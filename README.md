# Getting Started

### Superhero Demo API Reference Documentation

Before running, verify that you installed:
* Java 11
* Maven
* Docker
* Docker compose
* Makefile

### Running the standalone
* Clone the repository
* Run `cd superhero-service`
* Run `mvn srping-boot:run`
* Swagger API Doc Url: `http://localhost:8080/swagger-ui.html`
* H2 console: `http://localhost:8080/h2-console`
* Spring Security and H2 credentials location: `src/main/resources/application.properties`

### Running Docker container ###
* From repository root directory
* Run `make up`