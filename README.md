## Description

API is built with caching and PostgreSQL
Nothing is encrypted and the database should be created using the same config as `application.properties`
The API creates some dummy data on start just for testing.

## Documentation

Inside ``./postman`` there is a Postman collection if required

## Build

Simply execute `./build.bat` or `mvn clean package` to build the API

## Deploy

Execute `java -jar target/gaming-api-0.0.1-SNAPSHOT.jar`