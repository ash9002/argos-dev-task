package com.ashleymuswere.argos.resources;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Ashley on 07/02/2016.
 */

/**
 * Essentially the same as entity layer classes
 * however extends ResourceSupport which will allow the entity to be
 * converted into a resource using the ResourceAssemblerSupport classes in the
 * resource assembler layer. The ResourceSupport class is provided by
 * Spring HATEOAS to allow adding links such as "self" links to the entity when its
 * exposed/sent to the client
 */

public class RecentProductResource extends ResourceSupport{

    private String title;
    private double price;
    private String dealUrl;
    private String productUrl;
    private String description;
    private String imageUrl;
    private double temperature;
    private String submitTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDealUrl() {
        return dealUrl;
    }

    public void setDealUrl(String dealUrl) {
        this.dealUrl = dealUrl;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
