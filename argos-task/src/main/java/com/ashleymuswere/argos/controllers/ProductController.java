package com.ashleymuswere.argos.controllers;

import com.ashleymuswere.argos.entities.Product;
import com.ashleymuswere.argos.resourceasm.ProductResourceAssembler;
import com.ashleymuswere.argos.resources.ProductResource;
import com.ashleymuswere.argos.services.ProductService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.commons.math3.util.Precision;

import java.io.IOException;
import java.util.*;

/**
 * Created by Ashley on 03/02/2016.
 */

/**
 * This class is the Spring MVC controller that handles HTTP Requests for Product resources.
 * The resources are exposed the URIs specified by the @RequestMapping
 *
 */

@Controller
@RequestMapping("api")
public class ProductController {

    //Controller uses product service to call CRUD operations on resources
    @Autowired //@Autowired tells Spring application context to inject an instance of the product service here
    private ProductService productService;

    /**
     * This method uses the product service to find all products and returns a
     * list of product resources in a HttpResponse entity.
     * The method expects a HTTP GET request from the client.
     * Before returning a HTTP response, the method makes API calls to the
     * Argos and Ebay APIs and processes the JSON responses
     *
     * @return ResponseEntity, which is a HTTP response entity containing a HTTP status code and the product resource
     */
    @RequestMapping(method = RequestMethod.GET, value = "products")
    public HttpEntity<List<ProductResource>> getProducts() throws UnirestException, IOException {

        List<Product> productList = new ArrayList<Product>();
        List<String> urlList = new ArrayList<String>(); //Ebay URLs
        List<Double> priceList = new ArrayList<Double>(); //Ebay Prices

        Product entity;

        //Making API call to HUKD, specifying the request URL parameters
        HttpResponse<com.mashape.unirest.http.JsonNode> json = Unirest.get("http://api.hotukdeals.com/rest_api/v2")
                .header("accept", "application/json")
                .queryString("key", "d8bc02f4927bb3c8dae658097f715c6a")
                .queryString("output", "json")
                .queryString("merchant", "argos")
                .queryString("results_per_page", "30")
                .queryString("order", "hot")
                .queryString("forum","deals")
                .queryString("exclude_expired", "true")
                .asJson();

        //Parsing JSON returned from HUKD
        JSONObject obj = new JSONObject(json);

        JSONObject body = (JSONObject) obj.get("body");

        JSONArray array = body.getJSONArray("array");

        for(int i = 0; i < array.length(); i++) {
            JSONObject deals = (JSONObject) array.get(i);
            JSONObject items = (JSONObject) deals.get("deals");

            JSONArray array1 = items.getJSONArray("items");

            for (int j = 0; j < array1.length(); j++) {

                JSONObject product = (JSONObject) array1.get(j);

                entity = new Product();

                JSONObject tags = (JSONObject) product.get("tags");

                JSONArray tagsArray = tags.getJSONArray("items");

                String tagString = " ";

                //Concatenating the tags for each product item to use as input
                //to search ebay API
                for(int h = 0; h < tagsArray.length(); h++) {
                    JSONObject tagsObj = (JSONObject) tagsArray.get(h);

                    tagString = tagsObj.get("name") + " " + tagString;
                }

                //Setting product entity properties to those returned by HUKD

                entity.setTitle(product.get("title").toString().trim().replace("Â", "").replace("â","")); //remove unwanted characters
                if(!product.get("price").toString().trim().equals("null")) { //OMITTING  deals with no/null prices in the JSON
                    entity.setPrice(Precision.round(Double.parseDouble(product.get("price").toString().trim()),2));
                } else {
                    entity.setPrice(0.00); //For products with a null price, set the property to 0.00
                }
                entity.setDealUrl(product.get("deal_link").toString().trim());
                entity.setImageUrl(product.get("deal_image").toString().trim());
                entity.setDescription(product.get("description").toString().trim().replace("Â", "").replace("â","").replace("¬",""));
                entity.setProductUrl("http://www.hotukdeals.com/visit?m=5&q="+product.get("deal_image").toString().trim().substring(44,53));
                entity.setTemperature(Precision.round(Double.parseDouble(product.get("temperature").toString().trim()),2));

                //Making API call to Ebay API using the tags of the returned
                //HUKD products as the value for the 'keywords' URL parameter to find matching product
                HttpResponse<com.mashape.unirest.http.JsonNode> ebayJson = Unirest.get("http://svcs.ebay.com/services/search/FindingService/v1")
                        .header("accept", "application/json")
                        .queryString("SECURITY-APPNAME", "AshleyMu-a79d-420a-a406-859ef261f5ae")
                        .queryString("OPERATION-NAME", "findItemsByKeywords")
                        .queryString("SERVICE-VERSION", "1.0.0")
                        .queryString("GLOBAL-ID","EBAY-GB")
                        .queryString("RESPONSE-DATA-FORMAT", "JSON")
                        .queryString("keywords",tagString.replace("@","%20").replace("Argos","%20")) //%20 is encoding for blank space
                        .asJson();

                //Default values if price and url isn't found on ebay API (i.e product isn't found)
                String urlFinal = "NOT FOUND";
                Double finalPrice = 0.0;

                //Parsing JSON returned by Ebay API

                ObjectMapper mapper = new ObjectMapper();

                JsonNode root = mapper.readValue(ebayJson.getRawBody(), JsonNode.class);

                JsonNode item1 = root.path("findItemsByKeywordsResponse");

                JsonNode rootNode = item1.get(0);

                if(!rootNode.path("searchResult").isMissingNode()) {
                    JsonNode searchNode = rootNode.path("searchResult");

                    JsonNode searchNode2 = searchNode.get(0);

                    if(!searchNode2.path("item").isMissingNode()) {
                        JsonNode itemNode = searchNode2.path("item");

                        JsonNode itemNode2 = itemNode.get(0);

                        JsonNode urlNode = itemNode2.path("viewItemURL");

                        JsonNode urlNode2 = urlNode.get(0);

                        urlFinal = urlNode2.textValue();

                        JsonNode sellingStatus = itemNode2.get("sellingStatus");

                        JsonNode sellingStatus2 = sellingStatus.get(0);

                        JsonNode currentPrice = sellingStatus2.path("currentPrice");

                        JsonNode currentPrice2 = currentPrice.get(0);

                        finalPrice = Double.parseDouble(currentPrice2.path("__value__").textValue());
                    }

                }

                //Adding each URL for ebay product found into array list
                urlList.add(urlFinal);
                //Adding each price for ebay product found into array list
                priceList.add(Precision.round(finalPrice, 2));

                //setting price and url for product that I'll persist in the in-memory hibernate database
                entity.setEbayPrice(priceList.get(j));
                entity.setEbayUrl(urlList.get(j));

                //calculating difference by subtracting price from ebay price
                entity.setPriceDifference(Precision.round(entity.getEbayPrice()-entity.getPrice(), 2));

                //persisting product entity
                Product saved = productService.createProduct(entity);


                if(saved != null) {
                    productList.add(saved);
                }
            }

        }

        //Getting all products created
        productList = productService.findAllProducts();

        //Converting products to a list of product resources ready to be returned in the HTTP response
        List<ProductResource> products = new ProductResourceAssembler().toResources(productList);

        List<ProductResource> resources = products;

        //Returning list of product resources as JSON in HTTP response
        return new ResponseEntity<List<ProductResource>>(resources, HttpStatus.OK);

    }

}
