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
