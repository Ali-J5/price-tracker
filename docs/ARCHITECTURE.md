# System Architecture Overview

## Component Breakdown
1. **Cloud API (Spring Boot):** Manages tracked items, price logs, notifications, and worker authentication.
2. **Database (PostgreSQL):** Stores relational data for items and time-series price history.
3. **Local Worker (Python/Playwright):** Periodically polls the API for target URLs, extracts live prices on a residential IP, and reports data back.