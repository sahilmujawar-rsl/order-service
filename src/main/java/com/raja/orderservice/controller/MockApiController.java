package com.raja.orderservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/mock-api")
public class MockApiController {

    @Value("${external.api.key}")
    private String expectedApiKey;

    @GetMapping("/ping")
    public ResponseEntity<Map<String, String>> ping(@RequestHeader(value = "X-API-KEY", required = false) String apiKey) {
        if (apiKey == null || !apiKey.equals(expectedApiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(Map.of("status", "ok", "message", "pong"));
    }
}
