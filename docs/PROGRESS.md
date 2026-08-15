# Project Progress & Checkpoint Log

## [Phase 1.1] Environment & Documentation Setup
* **Status:** In Progress
* **Completed:** Repository initialized, `.gitignore` configured, baseline documentation structure established.

# Project Progress & Checkpoint Log

## [Phase 1.1] Environment & Documentation Setup
* **Status:** Completed
* **Completed:** Repository initialized, `.gitignore` configured, baseline documentation established, and pushed to GitHub `main`.

## [Phase 1.2] Scaffolding the Spring Boot API
* **Status:** Completed
* **Completed:** Generated Spring Boot 4.1 base project with Web and Actuator dependencies. Verified local Maven build. Code merged to `main`.

## [Phase 1.3] Dockerizing the API
* **Status:** Completed
* **Completed:** Created multi-stage `Dockerfile` using Java 21 Alpine. Successfully tested local container build. 

---
**Phase 1 Complete.**


## [Phase 2.1] PostgreSQL & Flyway Migrations
* **Status:** Completed
* **Completed:** Configured `docker-compose.yml` for local PostgreSQL. Added JPA and Flyway starter dependencies. Successfully executed `V1__init_schema.sql` to build `tracked_items` and `price_logs` tables.

## [Phase 2.2] JPA Entities & Repositories
* **Status:** Completed
* **Completed:** Created `TrackedItem` and `PriceLog` JPA entities with BigDecimal precision and Lazy loading. Created Spring Data Repositories for database access. Passed application context load tests.

---
**Phase 2 Complete.**


## [Phase 3.1] DTOs & Service Layer
* **Status:** Completed
* **Completed:** Implemented immutable Java 21 Records for `TrackedItem` DTOs. Built `TrackedItemService` to encapsulate business logic and integrated SLF4J structured logging for safe observability.

## [Phase 3.2] REST Controllers
* **Status:** Completed
* **Completed:** Built `TrackedItemController` handling GET and POST requests. Mapped incoming JSON to DTOs and returned proper `ResponseEntity` HTTP status codes. Validated endpoints locally via cURL.

## [Phase 3.3] API Key Security Middleware
* **Status:** Completed
* **Completed:** Implemented `ApiKeyFilter` extending `OncePerRequestFilter` to intercept `/api/` traffic. Enforced `X-API-KEY` header validation with configurable secrets via `@Value`. Verified `401 Unauthorized` and `200 OK` behaviors locally via cURL.

---
**Phase 3 Complete.**


## [Phase 4.1] Local Worker Environment Setup
* **Status:** Completed
* **Completed:** Initialized isolated Python `venv` inside the `/worker` directory. Installed `playwright` (Chromium) and `requests`. Generated `requirements.txt` and created baseline script.