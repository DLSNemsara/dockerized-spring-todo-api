# Dockerized Spring Boot Todo API

## Project Description
This project is a RESTful API for a simple Todo application, built with Spring Boot and containerized with Docker. The primary goal is to showcase a practical implementation of cloud-native principles, including a multi-stage Docker build, persistent data storage using volumes, and seamless inter-container communication on a custom Docker network.

This project was developed as a learning exercise provided by **Cloud Native Sri Lanka** to demonstrate core DevOps skills.

***

## Features
- **Full CRUD Functionality**: Create, Read, Update, and Delete operations for Todo items.
- **Optimized Docker Build**: A multi-stage Dockerfile ensures a lightweight and secure production image.
- **Persistent Logging**: Application logs are written to a named Docker volume (`/var/log/app`), ensuring they survive container restarts.
- **Containerized Database**: A PostgreSQL container serves as the database, with its data persisted in a separate named volume.
- **Custom Networking**: The Spring Boot API and PostgreSQL database communicate efficiently and securely over a custom user-defined bridge network.

***

## Tech Stack

| Category            | Technology                  |
|---------------------|-----------------------------|
| Language & Framework| Java 17, Spring Boot 3.5    |
| Build Tool          | Maven                       |
| Containerization    | Docker, Docker Compose      |
| Database            | PostgreSQL                  |
| Logging             | Logback                     |

***

## Architecture Overview
This application uses a simple, multi-container architecture managed by Docker Compose.

- The **Spring Boot App container** runs the Java API.
- The **PostgreSQL DB container** stores the application data.
- Both containers are connected to a custom bridge network named `todo-network`, allowing them to communicate via their service names (`app` and `db`).
- Two named volumes, `app-logs` and `db-data`, are used to persist logs and database information.

***

## Getting Started

### Prerequisites
- Docker Desktop
- Java 17+
- Maven

### Running the Application

**Clone the Repository**
```bash
git clone https://github.com/your-username/dockerized-spring-todo-api.git
cd dockerized-spring-todo-api
```

**Build and Run with Docker Compose**  
This command will build the Spring Boot application image and start both the application and database containers in detached mode.

```bash
docker compose up -d --build
```

**Verify the Containers are Running**

```bash
docker compose ps
```

You should see two containers (`app` and `db`) with a status of "Up".

**Access the API**  
The API will be available at [http://localhost:8080](http://localhost:8080).

-----

## API Endpoints

You can use a tool like Postman or `curl` to interact with the API.

| Method | Endpoint         | Description                 |
|--------|------------------|-----------------------------|
| GET    | `/api/todos`     | Retrieve all todos.         |
| GET    | `/api/todos/{id}`| Retrieve a single todo.     |
| POST   | `/api/todos`     | Create a new todo.          |
| PUT    | `/api/todos/{id}`| Update an existing todo.    |
| DELETE | `/api/todos/{id}`| Delete a todo.              |

**Example: Create a New Todo with curl**

```bash
curl -X POST http://localhost:8080/api/todos \
-H "Content-Type: application/json" \
-d '{"title": "Learn Docker Networking", "done": false}'
```

-----

## Reflection on Learning

This project was a practical deep-dive into two fundamental Docker concepts: persistent storage and networking. Using named volumes proved to be a straightforward yet powerful way to decouple a container's lifecycle from the data it generates, ensuring that critical information like logs and database files persists across restarts.

The real insight came from implementing a custom bridge network, which abstracts away container IP addresses and allows services to communicate securely using their defined names. This approach simplifies configuration and is a foundational pattern for building scalable, multi-service applications.