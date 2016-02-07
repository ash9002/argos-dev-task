package com.ashleymuswere.argos.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Ashley on 07/02/2016.
 */

@Entity
@Table(name = "RECENT_PRODUCT")
public class RecentProduct {

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
    private String submitTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getDealUrl() {
        return dealUrl;
    }

    public void setDealUrl(String dealUrl) {
        this.dealUrl = dealUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
