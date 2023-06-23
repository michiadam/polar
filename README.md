# polar
Project to reproduce Multi module maven project based on Quarkus

## See proposal on https://github.com/quarkusio/quarkus/issues/6266

## Modules

### quarkus-root
This module contains the quarkus maven plugin, all the required dependencies, and all the @quarkusTest

Integration tests are located on quarkus-root module for not having to deal with dependency inheritance... this way we're testing using the same dependencies available in runtime.
Unit testing should use mocking for accessing other modules, and must be placed on the correct module.


### repository
Handles data access. 

Database management (flyway or something...)

Contains JPA entities and DAOs

### service
Incorporates business logic, and handles unit of work (transactions)

Maps JPA entities to business entities

### resource
Delivers RESTful endpoints for dealing with business entities (resources).


## How to run the project

 On a terminal, under project root folder, run  the following command:
```
./mvnw compile quarkus:dev
```

Open a browser and navigate to:
 
```
http://0.0.0.0:8080/q/swagger-ui
```

Use the interactive swagger interface to execute the web service.


## How to test the project

Simply use the IDE to run a test, or don't maven skip them.

Notice that tests are re-testing things just for noticing that environment is fully set on every layer.
Real life tests should implement mocking.
