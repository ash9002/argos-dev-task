# argos-dev-task
Argos graduate developer task - Ashley Muswere

I've used a client-server REST architecture for the application. 

This was done with two main technologies: 
    1. Spring Framework (Spring HATEOAS, Spring Data JPA, Spring Web MVC) [Server]
    2. AngularJS [Client]

My Spring MVC RESTful API consumes JSON data from the HUKD API. The Spring API then processes the JSON
and exposes the transformed HUKD JSON data at the endpoint '/api/products'. This endpoint is accessed
by my AngularJS client application, which consumes the data in a using a HTTP GET request.