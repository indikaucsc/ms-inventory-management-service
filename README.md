# Inventory Management Microservice

This repository contains the **Inventory Management Microservice** for the **Pharmacy Management System**. This service is responsible for managing inventory items, including adding, updating, retrieving, and deleting inventory records.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Project Setup](#project-setup)
- [Configuration](#configuration)
    - [Database Configuration](#database-configuration)
    - [Environment-Specific Configurations](#environment-specific-configurations)
- [Running the Application](#running-the-application)
    - [Running Locally](#running-locally)
    - [Running with Docker](#running-with-docker)
    - [Running on Kubernetes](#running-on-kubernetes)
- [API Documentation](#api-documentation)
- [Health Check](#health-check)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

---

## Features

- **CRUD Operations**: Create, read, update, and delete inventory items.
- **RESTful API**: Exposes REST endpoints for interacting with inventory data.
- **Swagger Integration**: Provides interactive API documentation.
- **Health Monitoring**: Includes health check endpoints for monitoring service status.
- **Containerization**: Supports Docker for containerization and Kubernetes for orchestration.
- **Environment Management**: Configurations for different environments like SIT, UAT, and PROD.

## Technologies Used

- **Java 17**
- **Jakarta EE 9.1**
    - Jakarta RESTful Web Services (JAX-RS)
    - Jakarta Persistence (JPA)
    - Jakarta Contexts and Dependency Injection (CDI)
- **EclipseLink**: JPA implementation.
- **Jersey**: RESTful Web Services implementation.
- **Swagger (OpenAPI 3)**: API documentation.
- **H2 Database**: In-memory database for development and testing.
- **Docker**: Containerization.
- **Kubernetes**: Container orchestration.
- **Maven**: Build automation tool.

## Prerequisites

- **Java Development Kit (JDK) 17** or higher
- **Maven 3.6** or higher
- **Docker** installed and running (for containerization)
- **Kubernetes** cluster set up (for deployment on Kubernetes)
- **Git** installed (for cloning the repository)

## Project Setup

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/ms-inventory-management-service.git
cd ms-inventory-management-service
