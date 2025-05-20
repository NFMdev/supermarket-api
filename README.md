# Supermarket Optimized Shopping Route API

This project is a backend API for a supermarket shopping application that generates optimized shopping routes based on a user's product list. The goal is to improve the shopping experience by minimizing time spent navigating the supermarket.

## Features

- User authentication and authorization with JWT.
- Manage supermarkets, sections, products, and user shopping lists.
- Generate and store optimized shopping routes.
- Manage user favorite products.
- Role-based access control (e.g., admin vs. regular users).

## Technology Stack

- **Backend:** Spring Boot (Java)
- **Database:** PostgreSQL (relational data) and MongoDB (for storing routes)
- **Security:** Spring Security with JWT
- **Mapping:** ModelMapper for DTO conversions

## Status

This is an early version of the project. It may contain bugs and is subject to frequent changes as development progresses. Contributions and feedback are welcome.

## Getting Started

To run the project, configure your `application.properties` with the correct database and JWT settings.

```bash
./mvnw spring-boot:run
