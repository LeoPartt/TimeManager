# 🕒 Time Manager

## 📖 Overview
**Time Manager** is an employee management and time-tracking application designed to help companies monitor working hours, team performance, and employee punctuality.  
The project follows the **DevOps philosophy**, emphasizing automation, testing, and CI/CD practices.

Employees can clock in/out, view their activity summaries, and managers can access team KPIs, reports, and manage users.

---

## 🧱 Architecture

| Layer | Technology | Description |
|-------|-------------|-------------|
| **Frontend** | Flutter | Cross-platform mobile client using the REST API. |
| **Backend** | Java Spring Boot | RESTful API implementing the business logic and authentication. |
| **Database** | PostgreSQL | Relational database used for persistence. |
| **Reverse Proxy** | Nginx | Manages routing between frontend, backend, and database services. |
| **Containerization** | Docker & Docker Compose | Each service runs as a separate container. |
| **CI/CD** | GitHub Actions | Automated builds, tests, and coverage reports. |

---

## 🧩 Features

### 👤 Common (Employee)
- Login and logout securely (JWT authentication)
- Edit and delete personal account
- Clock in and clock out
- View working hours and dashboards

### 👨‍💼 Manager
- Manage teams (add, update, delete members)
- View team KPIs (e.g., lateness rate, total working hours)
- Access reports by date and employee
- Monitor employee dashboards

---

## ⚙️ Backend API

### Base URL
```http://localhost:<backend_port>/api```

### Main Endpoints
| Method | Endpoint | Description |
|--------|-----------|-------------|
| GET | `/users` | Retrieve all users |
| POST | `/users` | Add a new user |
| PUT | `/users/{id}` | Update user info |
| DELETE | `/users/{id}` | Delete a user |
| GET | `/teams` | Retrieve all teams |
| POST | `/teams` | Add a new team |
| PUT | `/teams/{id}` | Update a team |
| DELETE | `/teams/{id}` | Delete a team |
| POST | `/clocks` | Register arrival/departure |
| GET | `/users/{id}/clocks` | Retrieve user working history |
| GET | `/reports` | Get global KPI reports |

---

## 🔐 Authentication
All protected routes require a **JWT token** obtained after login.  
Passwords are hashed and user data is validated server-side.

---

## 🧪 Testing
- Unit and integration tests for API routes using Spring Boot’s testing framework.
- Flutter widget tests for core UI components.
- Test coverage is reported via **GitHub Actions CI**.

---

## 🐳 Docker Setup

### Development
Create a `docker-compose.yml` file with services for:
- `backend` (Spring Boot)
- `frontend` (Flutter build or web)
- `db` (PostgreSQL)
- `proxy` (Nginx)

Run all containers:
```bash
  docker-compose up --build
```

### Production

A separate docker-compose.prod.yml handles optimized builds and production configuration.

---

## ⚙️ Environment Variables
| Variable | Description |
|----------|-------------|
|POSTGRES_USER | PostgreSQL username |
|POSTGRES_PASSWORD | PostgreSQL password |
|POSTGRES_DB | Database name |
|SPRING_DATASOURCE_URL | JDBC connection URL |
|JWT_SECRET | Secret key for token generation |
|SERVER_PORT | Backend server port |

---

## 🚀 CI/CD Pipeline

1. Using **GitHub Actions**, the pipeline includes:
2. **Build** backend and frontend
3. **Run tests** and check coverage
4. **Generate artifacts** (APK or web build)
5. **Deploy** to the testing environment

---

## 💡 Good Practices

- Follows **DevOps methodology** (automation, continuous integration)
- Consistent **Git flow** with protected branches and code reviews
- Modular and scalable **Docker architecture**
- UX and **accessibility awareness** in UI design
- Clear **documentation and code structure**

---

## 🧾 License

This project is developed as part of the **Epitech school program** and is not intended for commercial use.

---

## 🧑‍💻 Authors

### **Team NAN_2**

- **Samuel Gauthier**
- **Léo Guerizec**
- **Maiva Magnifouet**
- **Quentin Faivret**
