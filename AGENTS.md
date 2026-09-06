# Base44 Dev Environment

## Project Overview

Traccar is a Java-based GPS tracking server (Gradle, Java 21, Jetty, Liquibase).
The web UI is a separate Vite/React app in the `traccar-web` submodule.

## Architecture

- **app** — Java backend built from source via `./gradlew assemble`, runs on port 8082 with an
  embedded H2 database (no external DB needed). Config is `debug.xml` at the repo root.
- **web** — Vite dev server (`traccar-web/`) on port 3000, proxies `/api` calls to the backend.
  This is the user-facing entry point exposed to the preview.

## Key Details

- The `traccar-web` submodule is cloned from `https://github.com/traccar/traccar-web.git` (the
  repo's `.gitmodules` uses a relative URL that doesn't resolve outside GitHub).
- `debug.xml` configures H2 (`jdbc:h2:./target/database`), web path `./traccar-web/simple`, and
  localization at `./traccar-web/src/resources/l10n`.
- The Vite proxy target is configurable via `VITE_API_TARGET` / `VITE_API_WS_TARGET` env vars
  (set in compose to `http://app:8082` / `ws://app:8082`).
- No external secrets are required — H2 is embedded and all other services are optional.

## Verification

- Health check: `curl -sf http://localhost:8082/api/server` (backend), `curl -sf http://localhost:3000/` (web).
- The preview shows the Traccar login screen. On first run the database is empty, so you register
  the first admin user via the web UI.
- Java changes require rebuilding: `docker compose -f docker-compose.base44.yml restart app`
  (the service rebuilds on restart via its command). Web changes hot-reload via Vite.
