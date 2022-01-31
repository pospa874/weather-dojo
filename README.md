Spring Boot Coding Dojo
---

Welcome to the Spring Boot Coding Dojo!

### Introduction

This is a simple application that requests its data from [OpenWeather](https://openweathermap.org/) and stores the
result in a database. The application can run in a docker container.

### The API key for OpenWeatherMap service

Get one for your own purpose at [OpenWeatherMap](https://openweathermap.org/appid) page.

### Build and run

Requirements: **maven**, **Java 11**, **PostgreSQL**, docker, docker-compose

Building a standard java application via maven from root of source codes
> mvn clean install

This command builds a jar file for either running directly locally or for a docker deployment.

To run the app locally you can execute `mvn spring-boot:run` and don't forget to have running PostgreSQL, you have to
setup environment variables
`DB_URL`(JDBC connection string to PostgreSQL), `DB_USER`, `DB_PASS`, `OPENWEATHERMAPAPPID`(API key above).

But this app could be run also without local installation of PostgreSQL using Docker and docker-compose.
> APP_ID=**REPLACE_API_KEY_HERE** docker-compose up --build

Using this command you can run the application directly. It is using also image of PostgreSQL. Feel free to use
documentation to docker compose, with option `-d` you can run it in the background. And `--build`
option can be omitted.

### Tests

Tests could be executed via maven, but keep it mind that there are also E2E integrations tests using real service, so it
is needed to either set `OPENWEATHERMAPAPPID` environment variable or modify `APP_ID`
in `src/test/resources/application.yaml`
Tests are using in memory H2 database, and it is not needed to provide any login.

### Logging

Logs are located in `logs` folder with rolling and archiving in the same folder.

### Requests and docs

There is a file located in `src/main/resources/requests.http` where you can see supported requests. It is also possible
to call the requests directly from the IDE (tested in IntelliJ IDEA) and there are also `curl` commands available.

It is possible to call `/weather` endpoint by `GET` method with required query param `city`(meaning the name of the city
where you want to get the weather). For example for local run in localhost and port 8080
> GET http://localhost:8080/weather?city=Amsterdam
>
>Accept: application/json

It is recommened to use `Accept` header `application/json`