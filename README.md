# Quiz-Application-Spring-Boot-Microservices
A distributed Quiz Application built with Spring Boot and Spring Cloud, featuring four microservices, Eureka Service Discovery, API Gateway, and Feign Client-based inter-service communication.

# Quiz Application – Spring Boot Microservices

A **Quiz Application** built using **Spring Boot and Spring Cloud Microservices Architecture**.

The project is divided into four independent microservices that communicate with each other through **Feign Client** and use **Eureka Server** for service discovery.

## Architecture

```text
                    ┌─────────────────┐
                    │   API Gateway   │
                    │     :8765       │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │ Eureka Server   │
                    │ Service Discovery│
                    │     :8761       │
                    └────────┬────────┘
                             │
               ┌─────────────┴─────────────┐
               │                           │
               ▼                           ▼
      ┌─────────────────┐        ┌─────────────────┐
      │  Quiz Service   │        │ Question Service│
      │     :8090       │───────▶│     :8081       │
      └─────────────────┘ Feign  └─────────────────┘
```

## Microservices

### 1. Quiz Service

Responsible for:

* Creating quizzes
* Retrieving quiz questions
* Submitting quiz responses
* Calculating quiz results
* Communicating with Question Service using Feign Client

### 2. Question Service

Responsible for:

* Managing questions
* Retrieving questions
* Providing questions for quizzes
* Calculating scores based on submitted responses

### 3. Service Discovery

Implemented using **Netflix Eureka Server**.

Responsibilities:

* Registering microservices
* Discovering available service instances
* Maintaining service information
* Supporting communication between services without hardcoding host and port

### 4. API Gateway

Acts as the single entry point for client requests.

Responsibilities:

* Routing requests to appropriate microservices
* Service discovery-based routing
* Providing a centralized entry point to the application

## Technologies Used

* Java
* Spring Boot
* Spring Cloud
* Spring Cloud Netflix Eureka
* Spring Cloud Gateway
* Spring Cloud OpenFeign
* Spring Data JPA
* REST APIs
* MySQL
* Maven

## Inter-Service Communication

The services communicate using **Feign Client**.

For example, the Quiz Service communicates with the Question Service through a Feign interface:

```java
@FeignClient("QUESTION-SERVICE")
public interface QuestionInterface {

    @PostMapping("/question/getScore")
    Integer getScore(@RequestBody List<Response> responses);
}
```

This allows the Quiz Service to communicate with Question Service using its **service name** instead of directly hardcoding its IP address and port.

## Service Ports

| Service          | Port |
| ---------------- | ---: |
| Eureka Server    | 8761 |
| API Gateway      | 8765 |
| Quiz Service     | 8090 |
| Question Service | 8081 |

## Request Flow

A typical request flows through the system like this:

```text
Client
  │
  ▼
API Gateway
  │
  ▼
Eureka Service Discovery
  │
  ▼
Quiz Service
  │
  │ Feign Client
  ▼
Question Service
```

For example, when submitting a quiz:

```text
POST /quiz/submit/{id}
        │
        ▼
   Quiz Service
        │
        │ Feign Client
        ▼
Question Service
        │
        ▼
    Calculate Score
```

## Running the Project

Start the services in the following order:

### 1. Service Discovery

Start the Eureka Server first.

```text
http://localhost:8761
```

### 2. Question Service

Start the Question Service.

```text
http://localhost:8081
```

### 3. Quiz Service

Start the Quiz Service.

```text
http://localhost:8090
```

### 4. API Gateway

Start the API Gateway.

```text
http://localhost:8765
```

Once all services are running, they will register with Eureka Service Discovery.

## Key Microservices Concepts Demonstrated

This project demonstrates practical implementation of:

* Microservices Architecture
* Service Discovery
* Eureka Server
* API Gateway
* Feign Client
* Inter-service Communication
* REST APIs
* Service Registration
* Multiple Service Instances
* Load-balanced service communication
* Spring Boot
* Spring Data JPA
* Database Integration

## Project Structure

```text
Quiz-Application/
│
├── quiz-service/
│
├── question-service/
│
├── service-discovery/
│
└── api-gateway/
```

## Future Improvements

Possible improvements include:

* Centralized configuration using Spring Cloud Config
* Circuit breaker using Resilience4j
* Authentication and authorization using Spring Security/JWT
* Docker containerization
* Distributed tracing
* Centralized logging
* CI/CD pipeline

## Author

Developed as a practical **Spring Boot Microservices** project to understand service discovery, API Gateway, and inter-service communication using Feign Client.
