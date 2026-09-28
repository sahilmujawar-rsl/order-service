package com.raja.orderservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MockApiClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${external.api.key}")
    private String apiKey;

    @Value("${external.api.base-url:http://localhost:8080/mock-api}")
    private String baseUrl;

    public String ping() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", apiKey);
        HttpEntity<Void> request = new HttpEntity<>(headers);
        return restTemplate.exchange(baseUrl + "/ping", org.springframework.http.HttpMethod.GET, request, String.class).getBody();
    }
}
