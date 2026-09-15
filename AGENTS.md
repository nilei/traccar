# Base44 Development Environment

## Overview

This is the Traccar GPS tracking server (Java 21, Gradle). The web frontend is a git submodule (`traccar-web`).

## Setup

- **Runtime**: Java 21 (via `eclipse-temurin:21-jdk` Docker image)
- **Database**: H2 embedded (no external DB service needed)
- **Config**: `debug.xml` (dev config with H2, web debug mode)
- **Web port**: 8082 inside container, mapped to host port 3000

## Running

```bash
docker compose -f docker-compose.base44.yml up -d
```

The app builds the jar with `./gradlew copyDependencies jar`, then runs `java -jar target/tracker-server.jar debug.xml`.

## Testing

```bash
docker compose -f docker-compose.base44.yml exec app ./gradlew test --tests <TestClass> --console=plain
```

## Notes

- The `traccar-web` submodule must be initialized: `git submodule update --init --recursive`
- Protocol decoder tests use real binary frames and `verifyAttribute`/`verifyPosition` helpers from `ProtocolTest`
- Speed values in the Xexun3 protocol are in 0.1 km/h units (divide by 10 per API doc)
