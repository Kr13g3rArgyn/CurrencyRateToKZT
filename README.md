# Currency Rate Tracker

## Overview

The **Currency Rate Tracker** is a Spring Boot-based application designed to track and display exchange rates for various currencies over specific time periods. The application integrates with multiple currency exchange APIs to fetch real-time and historical data, focusing on providing exchange rates for USD, EUR, and RUB to KZT.

## Features

- **Real-time Exchange Rates**: Fetches the latest exchange rates from various reliable sources.
- **Historical Data**: Tracks and stores exchange rates over the last 10 days.
- **Customizable API Integrations**: Easily configurable to connect with different currency exchange APIs.
- **RESTful API**: Provides a REST API for accessing exchange rate data.
- **Spring Boot & Hibernate**: Built with Spring Boot for rapid development and Hibernate for efficient data handling.
- **Docker Support**: Containerized with Docker for easy deployment.

## Technologies Used

- **Java 17**
- **Spring Boot**
- **Hibernate / JPA**
- **PostgreSQL**
- **Docker**
- **Gson** (for JSON parsing)
- **RestTemplate** (for API calls)

## Getting Started

### Prerequisites

- Java 17
- Docker
- PostgreSQL

### Setup

1. **Clone the repository:**

   ```sh
   git clone https://github.com/Kr13g3rArgyn/CurrencyRateToKZT.git
2. **Update the configuration:**
Edit the application.yml file to set up your API keys and database connection.

3. **Build the project:**
```sh
./mvnw clean install
```
4. **Run the Docker container for PostgreSQL:**
```docker
docker-compose up -d
```
5. **Run the application:**
```sh
./mvnw spring-boot:run
```
## API Enpoints
- Get Latest Exchange Rates:
```http
GET /api/v1/currency-rate
```
- Get Exchange rates for a Specific Period:
```http
GET /api/v1/currency-rate/period
```
