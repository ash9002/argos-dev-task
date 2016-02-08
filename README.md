# argos-dev-task
Argos graduate developer task - Ashley Muswere.

This web application fetches and displays Argos product data from the http://www.hotukdeals.com API for the top 10 'hottest' products. The Argos product prices are compared with prices for products on **ebay.co.uk** using the eBay API in a similar way. Basic product data and URLs to the HUKD, Argos and Ebay products are displayed in a tabular format.

##How To Run

Please contact me on **ashmuswere@gmail.com** if there's any issues running the web application.

### OS X or Linux
1. Download zip file from project's GitHub and unzip

2. Navigate to argos **/argos-dev-task-master/argos-task/src/main/resources/static** and (Assuming NodeJS is installed) run **npm install** in the terminal

3. From the **/argos-dev-task-master/argos-task** directory, run the **./gradlew bootRun** command in the terminal. This starts the Jetty server on port 8080

4. In a browser such as Chrome or Firefox, navigate to **http://localhost:8080/app/index.html**

### Windows
1. Download zip file from project's GitHub

2. Navigate to argos **/argos-dev-task-master/argos-task/src/main/resources/static** and (Assuming NodeJS is installed) run **npm install** in the terminal

3. From the **/argos-dev-task-master/argos-task** directory, run the **gradlew bootRun** command on the command line. This starts the Jetty server on port 8080

4. In a browser such as Chrome or Firefox, navigate to **http://localhost:8080/app/index.html**

###Note: 
Loading and processing the JSON data to show the hottest deals may take up to 30 seconds

##Additional Features
- Able to sort by all criteria accept 'image'
- Able to view separate table containing most recently added deals 
- Able to access the URL for the competitor product

##Architecture
I've used a client-server REST architecture for the application. 

This was done with two main technologies: 

1. Spring Framework (Spring HATEOAS, Spring Data JPA, Spring Web MVC) [Server]
2. AngularJS [Client]

My Spring MVC RESTful API consumes JSON data from the HUKD API. The Spring API then processes the JSON
and exposes the transformed HUKD JSON data at the endpoint '/api/products'. This endpoint is accessed
by my AngularJS client application, which consumes the JSON over HTTP.

Example call to '/api/products' provides following response body:
```javascript
[{
    "title": "Babyliss Tourmaline Gold hair straighteners Half Price @ Argos 14.99",
    "price": 14.99,
    "dealUrl": "http://www.hotukdeals.com/deals/babyliss-tourmaline-gold-hair-straighteners-half-price-argos-14-99-2389509?aui=1070",
    "productUrl": "http://www.hotukdeals.com/visit?m=5&q=2389509_1",
    "description": "Excellent reviews.. free click and collect.",
    "imageUrl": "http://static.hotukdeals.com/images/threads/2389509_1.jpg",
    "temperature": 238.35,
    "ebayPrice": 0,
    "ebayUrl": "NOT FOUND",
    "priceDifference": -14.99,
    "links": []
  },
  //etc.
  ]
```
