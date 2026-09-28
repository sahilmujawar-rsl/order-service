# order-service (starter)

Starter project for the Secrets Management assignment.

## What's included
- Basic Spring Boot app (`OrderController` + `Order` entity, backed by PostgreSQL)
- Mock external API (`GET /mock-api/ping`, expects header `X-API-KEY`)
- `MockApiClient` that calls the mock API using the key from `application.properties`
- Sample hardcoded DB credentials and API key in `application.properties`

## Run
1. Make sure PostgreSQL and Vault are set up per the assignment doc.
2. `mvn spring-boot:run`
3. Test the mock API: `curl -H "X-API-KEY: sample-api-key" http://localhost:8080/mock-api/ping`

## Your task
Replace the hardcoded values in `application.properties` with Vault-managed secrets, per the assignment tasks.
