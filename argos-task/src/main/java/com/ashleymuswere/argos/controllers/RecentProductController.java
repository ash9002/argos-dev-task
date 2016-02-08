package com.ashleymuswere.argos.controllers;

import com.ashleymuswere.argos.entities.RecentProduct;
import com.ashleymuswere.argos.resourceasm.RecentProductResourceAssembler;
import com.ashleymuswere.argos.resources.RecentProductResource;
import com.ashleymuswere.argos.services.RecentProductService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.math3.util.Precision;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashley on 07/02/2016.
 */

/**
 * This class is the Spring MVC controller that handles HTTP Requests for Product resources
 * for RECENTLY added deals.
 * The resources are exposed the URIs specified by the @RequestMapping
 *
 */
@Controller
@RequestMapping("api")
public class RecentProductController {

    @Autowired
    private RecentProductService recentProductService;

    @RequestMapping(method = RequestMethod.GET, value = "recent-products")
    public HttpEntity<List<RecentProductResource>> createProduct() throws UnirestException, IOException {

        List<RecentProduct> list = new ArrayList<RecentProduct>();

        RecentProduct entity;

        //Making API call to HUKD, specifying the request URL parameters
        HttpResponse<JsonNode> json = Unirest.get("http://api.hotukdeals.com/rest_api/v2")
                .header("accept", "application/json")
                .queryString("key", "d8bc02f4927bb3c8dae658097f715c6a")
                .queryString("output", "json")
                .queryString("merchant", "argos")
                .queryString("results_per_page", "30")
                .queryString("order", "new")
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

                entity = new RecentProduct();

                JSONObject tags = (JSONObject) product.get("tags");

                JSONArray tagsArray = tags.getJSONArray("items");

                String tagString = " ";

                for(int h = 0; h < tagsArray.length(); h++) {
                    JSONObject tagsObj = (JSONObject) tagsArray.get(h);

                    tagString = tagsObj.get("name") + " " + tagString;


                }

                //Setting product entity properties to those returned by HUKD
                entity.setTitle(product.get("title").toString().trim().replace("Â", "").replace("â",""));
                if(!product.get("price").toString().trim().equals("null")) { //OMITTING  deals with no prices in the JSON
                    entity.setPrice(Precision.round(Double.parseDouble(product.get("price").toString().trim()),2));
                } else {
                    entity.setPrice(1.0); //For products with a null price, set the property to -1
                }
                entity.setDealUrl(product.get("deal_link").toString().trim());
                entity.setImageUrl(product.get("deal_image").toString().trim());
                entity.setDescription(product.get("description").toString().trim().replace("Â", "").replace("â","").replace("¬",""));
                entity.setProductUrl("http://www.hotukdeals.com/visit?m=5&q="+product.get("deal_image").toString().trim().substring(44,53));
                entity.setTemperature(Precision.round(Double.parseDouble(product.get("temperature").toString().trim()),2));
                entity.setSubmitTime(product.get("submit_time").toString().trim());

                //persisting product entity
                RecentProduct saved = recentProductService.createProduct(entity);


                if(saved != null) {
                    list.add(saved);
                }
            }

        }
        //Getting all products created
        list = recentProductService.findAllProducts();

        //Converting products to a list of product resources ready to be returned in the HTTP response
        List<RecentProductResource> products = new RecentProductResourceAssembler().toResources(list);

        List<RecentProductResource> resources = products;

        //Returning list of product resources as JSON in HTTP response
        return new ResponseEntity<List<RecentProductResource>>(resources, HttpStatus.OK);

    }
}
