# argos-dev-task
Argos graduate developer task - Ashley Muswere

##How To Run

### OS X or Linux
1. From the 'argos-task' directory, run './gradlew bootRun' command in the terminal. This starts the Jetty server on port 8080

2. In a browser such as Chrome or Firefox, navigate to localhost:8080/app/index.html

### Windows
1. From the 'argos-task' directory, run 'gradlew bootRun' command on the command line. This starts the Jetty server on port 8080

2. In a browser such as Chrome or Firefox, navigate to localhost:8080/app/index.html

###Note: 
Loading and processing the JSON data to show the hottest deals may take up to 30 - 45 seconds

##Architecture
I've used a client-server REST architecture for the application. 

This was done with two main technologies: 

1. Spring Framework (Spring HATEOAS, Spring Data JPA, Spring Web MVC) [Server]
2. AngularJS [Client]

My Spring MVC RESTful API consumes JSON data from the HUKD API. The Spring API then processes the JSON
and exposes the transformed HUKD JSON data at the endpoint '/api/products'. This endpoint is accessed
by my AngularJS client application, which consumes the JSON over HTTP.