# Notification Service

A secure backend Notification Service built using Spring Boot that allows sending and retrieving user notifications through REST APIs.  
The project follows clean layered architecture and uses JWT for authentication.

---

## 🚀 Features
- Send notifications to users via REST API
- Retrieve notifications by user ID
- JWT-based authentication and authorization
- In-memory H2 database for development and testing
- Clean layered architecture (Controller, Service, Repository)

---

## 🛠 Tech Stack
- Java
- Spring Boot
- Spring Security (JWT)
- Spring Data JPA
- H2 Database
- Maven

---

## 📌 API Endpoints
- `POST /api/auth/register` – Register a new user
- `POST /api/auth/login` – Authenticate user and get JWT token
- `POST /api/notifications` – Send notification (secured)
- `GET /api/notifications/user/{userId}` – Get notifications by user (secured)

---

## 🗄 Database
- Uses H2 in-memory database for rapid development
- Can be easily switched to MySQL/PostgreSQL for production

---

## 📖 Purpose
This service acts as a core notification backend that can later be integrated with
Email, SMS, or Push Notification systems.

---

## 👨‍💻 Author
Harsh Katiyar
