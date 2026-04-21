# network-device-api

A lightweight REST API for **network device inventory management**, built with Java 17 and Spring Boot. Fully containerised with Docker and equipped with a GitHub Actions CI/CD pipeline.

> Built as a personal project to explore cloud-native development patterns — Docker multi-stage builds, CI/CD automation, and RESTful API design.

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3 |
| Containerisation | Docker (multi-stage build) |
| Orchestration | Docker Compose |
| CI/CD | GitHub Actions |
| Testing | JUnit 5, MockMvc |

---

## API Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/health` | Health check |
| GET | `/api/devices` | List all devices |
| GET | `/api/devices/{id}` | Get device by ID |
| POST | `/api/devices` | Register a new device |
| PUT | `/api/devices/{id}/status` | Update device status |
| DELETE | `/api/devices/{id}` | Remove a device |

---

## Run Locally

**With Docker Compose (recommended):**
```bash
docker compose up --build
```

**Without Docker:**
```bash
mvn spring-boot:run
```

API available at `http://localhost:8080`

---

## CI/CD Pipeline

The GitHub Actions workflow (`.github/workflows/ci.yml`) runs on every push to `main` or `develop`:

1. **Test** — compiles the project and runs all unit tests
2. **Docker Build & Push** — builds the image and pushes to Docker Hub (main branch only)
3. **Smoke Test** — spins up the container and hits `/api/health` to verify the deployment

---

## Example Request

```bash
# List all devices
curl http://localhost:8080/api/devices

# Update status
curl -X PUT "http://localhost:8080/api/devices/rt-01/status?status=online"
```
