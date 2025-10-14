# Development Environment - Quick Start Security Guide

> **TL;DR**: This setup provides a secure development environment while maintaining full IDE access and hot reload capabilities.

## Network Architecture

```
Your Machine
├─ Browser → http://localhost (nginx on port 80)
│             ├─ /api/* → Backend (internal)
│             └─ /* → Frontend (internal)
│
└─ IDE → localhost:5432 → PostgreSQL (localhost only)
```

### Key Security Features
- ✅ **Single entry point**: Everything goes through nginx
- ✅ **DB secured**: Only accessible from your machine (127.0.0.1)
- ✅ **Non-root containers**: All services run as unprivileged users
- ✅ **Rate limiting**: 100 req/min on API, 200 req/min on frontend

---

## Quick Start

### 1. Start the stack
```bash
docker compose up -d
docker compose logs -f  # Watch the logs
```

### 2. Access the services
| Service      | URL                                    | Notes                   |
|--------------|----------------------------------------|-------------------------|
| **Frontend** | `http://localhost`                     | Main application        |
| **API**      | `http://localhost/api/`                | Backend API             |
| **Swagger**  | `http://localhost/api/swagger-ui.html` | API documentation       |
| **Database** | `localhost:5432`                       | For IDE connection only |

### 3. Connect your IDE to the database
```
Host: localhost
Port: 5432
Database: time_manager_db
User: postgres_dev
Password: DevPassword123!ChangeInProd
```

---

## Configuration Tips

### Enable Backend Debugging
Add to `compose.yaml` if you need to debug the Spring Boot backend:
```yaml
backend:
  ports:
    - "127.0.0.1:5005:5005"
  environment:
    JAVA_TOOL_OPTIONS: "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"
```
Then configure Remote JVM Debug in IntelliJ (localhost:5005).

### Enable SQL Logging
Add to `.env` if you need to see SQL queries:
```bash
SPRING_JPA_SHOW_SQL=true
```
Then: `docker compose restart backend`

---

## Credentials & Security

### ⚠️ Important Rules
1. **NEVER commit `.env`** → It's gitignored
2. **NEVER use dev credentials in production**
3. All credentials are in `.env` file

### Development Credentials
```bash
DB: postgres_dev / DevPassword123!ChangeInProd
Admin: DevAdmin123!ChangeInProd
JWT_SECRET: (see .env file - 64 char base64)
```

## Common Issues

### Compose won't start or services keep restarting
```bash
# Clean everything and rebuild
docker compose down -v  # -v removes volumes (including DB data)
docker compose up --build  # --build rebuilds images

# This fixes most startup issues by starting fresh
# Note: You'll lose your database data with -v
```

### Can't connect to database from IDE
```bash
# Check port binding
docker compose port db 5432  # Should show: 127.0.0.1:5432

# Use 127.0.0.1 instead of localhost if connection fails
# Verify credentials in .env match your IDE config
```

### Backend returns 502 Bad Gateway
```bash
# Check backend logs
docker compose logs backend --tail=50

# Wait for healthcheck (40s start_period)
curl http://localhost/api/actuator/health
```

### Rate limiting (429 Too Many Requests)
Temporarily increase limits in `nginx/nginx.conf`:
```nginx
limit_req_zone $binary_remote_addr zone=api_limit:10m rate=500r/m;
```

### Permission denied errors
Containers run as non-root (UID 1001). Fix volume permissions:
```bash
sudo chown -R 1001:1001 ./frontend/time_manager
```

---

**Last updated**: 2025-10-13
