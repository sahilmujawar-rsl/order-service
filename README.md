# order-service

Spring Boot app backed by PostgreSQL. Database credentials and the external API
key are read from HashiCorp Vault at startup.

## Endpoints

| Method | Path | Notes |
|---|---|---|
| GET | `/orders` | List orders |
| POST | `/orders` | Create an order |
| GET | `/mock-api/ping` | Requires the `X-API-KEY` header; 401 without it |

## Running

PostgreSQL and a local Vault server must be running. The application reads its
configuration from Vault, so it needs a token that can read
`database/creds/order-service-role` and `secret/data/order-service`.

```bash
export VAULT_ADDR=http://127.0.0.1:8200
export VAULT_TOKEN=$(vault token create -policy="order-service-policy" -ttl="1h" -field=token)
mvn spring-boot:run
```

Check the mock API by reading the key from Vault rather than typing it:

```bash
curl -i -H "X-API-KEY: $(VAULT_TOKEN=$VAULT_TOKEN vault kv get -field=external.api.key secret/order-service)" \
  http://localhost:8080/mock-api/ping
```

## Secrets

`application.properties` holds no credentials. It contains only the Vault
address, the KV and database backend settings, the role name, and the JDBC URL.

- Database credentials are generated per request by the Database Secrets Engine
  and are tied to a lease. Each one is a separate PostgreSQL user that Vault
  drops when the lease ends.
- `external.api.key` is read from `secret/order-service`.
- The token is passed through `VAULT_TOKEN`, so it is not stored in the
  repository. The root token is used for setup only and is not given to the
  application.
- `ddl-auto=validate` keeps the application from creating tables, since a
  dynamic user cannot own them and Vault would then fail to drop the role.

## Notes

The Maven wrapper is not tracked in git, so a fresh clone needs `mvn`.
