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

##Architecture and Approach Overview
I've used a client-server REST architecture for the application. 

This was done with two main technologies: 

1. Spring Framework (Spring HATEOAS, Spring Data JPA, Spring Web MVC) [Server]
2. AngularJS [Client]

My Spring MVC RESTful API consumes JSON data from the HUKD API and eBay API. The Spring API then processes the JSON
and exposes a JSON array of custom product objects derived from HUKD and Ebay at the endpoint '/api/products' or 'api/recent-products'. These endpoints are accessed by my AngularJS client application, which consumes the JSON over HTTP.

Much of the processing is done in the **controller** layer on the server:

I made HTTP requests to HUKD and Ebay using the Unirest library. Key URL parameters are:
```java
//omitted code
.queryString("merchant", "argos")
                .queryString("results_per_page", "30")
                .queryString("order", "hot")
                .queryString("forum","deals")
                .queryString("exclude_expired", "true")
//omitted code
```
This gets 30 deals that are currently 'hot' on the Argos HUKD page. For each product returned, I concatenate the strings in the 'tags' array of the HUKD JSON to generate a set of keywords that I then use as input for the Ebay HTTP Request to search for products by keyword. I parse the JSON returned from HUKD and Ebay to extract the relevant values and use them to create new instances of my own product entities. I use the entities to create REST resources that are exposed on the 'api/products' or 'api/recent-products' URIs.

Note: If a product is **not** found on the Ebay API using the strings of keywords mentioned above, I set the 'Ebay URL' value to 'NOT FOUND' and 'Ebay Price' value to '£0.00' for that product.

I've used Spring Data JPA to persist the entities generated from the API calls to HUKD and Ebay in an in-memory HSQLDB database.

Example call to '/api/products' provides an array of JSON objects such as:
```javascript
  [{
    "title": "Hisense 50EC591U 50 Inch 4K Ultra HD Smart LED TV - £419.99 At Argos",
    "price": 419.99,
    "dealUrl": "http://www.hotukdeals.com/deals/hisense-50ec591u-50-inch-4k-ultra-hd-smart-led-tv-419-99-argos-2389427?aui=1070",
    "productUrl": "http://www.hotukdeals.com/visit?m=5&q=2389427_1",
    "description": "Hisense 50EC591U 50 Inch 4K Ultra HD Smart LED TV\n\nhttp://www.argos.co.uk/beta/static/Product/partNumber/4247072.htm\n\nURL seems broke, might want to use the one here\n- DsK",
    "imageUrl": "http://static.hotukdeals.com/images/threads/2389427_1.jpg",
    "temperature": 411.45,
    "ebayPrice": 425,
    "ebayUrl": "http://www.ebay.co.uk/itm/Hisense-50-Inch-Smart-4K-Ultra-HD-LED-TV-UB50EC591UWTSUK-/222008559772",
    "priceDifference": 5.01,
    "links": []
  },
  //etc
  ]
```

On the client (AngularJS) I receive the JSON data after making an AJAX call to my API from the Angular controller layer. I first sort the data returned by temperature and then store the sorted JSON array in the $scope object (model), taking the 10 products with the highest temperatures. The sorted JSON is accessed in my view layer from the $scope where I display the information in a table that can be sorted all criteria accept for 'image'. I used angular-tablesort to implement the sorting of the table data.

The 'links' property is present in the product objects because of the use of Spring HATEOAS, which allows adding links to other related resources to allow the client to navigate my REST API completely via hypermedia. However, there was no requirement for this in this project and so the 'links' array is empty.
