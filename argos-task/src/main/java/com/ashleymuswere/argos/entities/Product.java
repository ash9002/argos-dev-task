package com.ashleymuswere.argos.entities;

import javax.persistence.*;

/**
 * Created by Ashley on 03/02/2016.
 */

@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @Column(length = 10000)
    private String title;

    @Column(length = 10000)
    private double price;

    @Column(length = 10000)
    private String dealUrl;

    @Column(length = 10000)
    private String productUrl;

    @Column(length = 10000)
    private String description;

    @Column(length = 10000)
    private String imageUrl;

    @Column(length = 10000)
    private double temperature;

    @Column(length = 10000)
    private double ebayPrice;

    @Column(length = 10000)
    private String ebayUrl;

    @Column(length = 10000)
    private double priceDifference;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getEbayPrice() {
        return ebayPrice;
    }

    public void setEbayPrice(double ebayPrice) {
        this.ebayPrice = ebayPrice;
    }

    public String getEbayUrl() {
        return ebayUrl;
    }

    public void setEbayUrl(String ebayUrl) {
        this.ebayUrl = ebayUrl;
    }

    public double getPriceDifference() {
        return priceDifference;
    }

    public void setPriceDifference(double priceDifference) {
        this.priceDifference = priceDifference;
    }

}
