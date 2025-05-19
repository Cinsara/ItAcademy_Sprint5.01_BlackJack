# 🃏 Blackjack Game API (Spring Boot + WebFlux)

This project implements a **reactive API** for a **Blackjack game** using **Spring Boot** and **Spring WebFlux**. The application connects to two databases: **MySQL** and **MongoDB**, and offers full game functionality including player management, gameplay logic, rankings, and game sessions.

## 🚀 Features

- ♻️ Fully reactive architecture using Spring WebFlux  
- 🛢️ Dual database configuration: MongoDB (Reactive) & MySQL  
- 🧩 RESTful API with CRUD operations  
- 📄 Swagger for automatic API documentation  
- 🧪 Unit testing with JUnit 
- 🧯 Global exception handling  
- 🐳 Docker support for containerization  

---

## 🧠 Architecture Overview

- **Reactive Stack**: Built using Spring WebFlux for full reactivity
- **MongoDB**: Stores game sessions and hands
- **MySQL**: Stores persistent player data and rankings
- **Exception Handling**: Managed globally via a `GlobalExceptionHandler`
- **Tests**: Unit tests for controllers and services using JUnit
- **Documentation**: Swagger UI enabled for easy API exploration

---

## 📡 Endpoints

### 🎮 Create Game
- **Method**: `POST`
- **Endpoint**: `/game/new`
- **Description**: Creates a new Blackjack game
- **Request Body**: Player name (playerName)
- **Response**: `201 Created` with game info

---

### 🕵️ Get Game Details
- **Method**: `GET`
- **Endpoint**: `/game/{id}`
- **Description**: Retrieves game details by ID
- **Response**: `200 OK` with full game status

---

### 🃏 Play a Move
- **Method**: `POST`
- **Endpoint**: `/game/{id}/play`
- **Description**: Plays a move in an existing game
- **Request Body**: Move data (actionType)
- **Response**: `200 OK` with updated game state

---

### ❌ Delete Game
- **Method**: `DELETE`
- **Endpoint**: `/game/{id}/delete`
- **Description**: Deletes an existing game
- **Response**: `204 No Content` if successful

---

### 🏆 Get Player Rankings
- **Method**: `GET`
- **Endpoint**: `/ranking`
- **Description**: Returns a leaderboard sorted by performance
- **Response**: `200 OK` with ranking list

---

### ✏️ Update Player Name
- **Method**: `PUT`
- **Endpoint**: `/player/{playerId}`
- **Description**: Updates a player's name
- **Request Body**: New player name
- **Response**: `200 OK` with updated player data

---

## 🧪 Testing

- Controller and service layer tested with `JUnit`
- Tests ensure correct database interactions and API behavior

---

## 📘 Swagger UI

- Swagger automatically generated for API endpoints  
- Visit `/swagger-ui.html` once the app is running

---

## 🐳 Docker Support

You can run the app inside a Docker container:

### Steps:

1. Create a `Dockerfile` at the root of the project
2. Add a `.dockerignore` file
3. Install Docker and log in
4. Build Docker image  
   ```bash
   docker build -t blackjack-api .
   ```
5. Run container  
   ```bash
   docker run -p 8080:8080 blackjack-api
   ```
6. Tag the image  
   ```bash
   docker tag blackjack-api yourusername/blackjack-api
   ```
7. Log in to Docker Hub  
   ```bash
   docker login
   ```
8. Push the image  
   ```bash
   docker push yourusername/blackjack-api
   ```
9. Pull and run the image anywhere 🎉

---

## 🐳 Docker Pull/Run

In order to use this project, you need to do these steps:

```bash
docker pull shycactus/blackjack-app:latest
docker run -p 8080:8080 shycactus/blackjack-app
```

---

## 📁 Project Structure

```
📦 blackjack-api
 ┣ 📂 src
 ┃ ┣ 📂 main
 ┃ ┃ ┣ 📂 java
 ┃ ┃ ┃ ┗ 📦 com.example.blackjack
 ┃ ┃ ┃   ┣ 📂 controllers
 ┃ ┃ ┃   ┣ 📂 dto
 ┃ ┃ ┃   ┣ 📂 services
 ┃ ┃ ┃   ┣ 📂 model
 ┃ ┃ ┃   ┣ 📂 cutils
 ┃ ┃ ┃   ┗ 📂 exceptions
 ┃ ┃ ┗ 📂 resources
 ┃ ┃   ┗ application.yml
 ┃ ┗ 📂 test
 ┃   ┗ 📂 java
 ┃     ┗ 📦 com.example.blackjack
 ┗ Dockerfile
 ┗ .dockerignore
 ┗ pom.xml
 ┗ README.md
```

---

## 🛠️ Technologies

- Java 17+
- Spring Boot 3+
- Spring WebFlux
- MongoDB Reactive Driver
- MySQL Connector
- Swagger / OpenAPI
- JUnit 5
- Docker

---
