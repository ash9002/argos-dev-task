package com.ashleymuswere.argos.resourceasm;

import com.ashleymuswere.argos.controllers.RecentProductController;
import com.ashleymuswere.argos.entities.RecentProduct;
import com.ashleymuswere.argos.resources.RecentProductResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by Ashley on 07/02/2016.
 */
public class RecentProductResourceAssembler extends ResourceAssemblerSupport<RecentProduct, RecentProductResource> {

    public RecentProductResourceAssembler() {
        super(RecentProductController.class, RecentProductResource.class);
    }

    @Override
    public RecentProductResource toResource(RecentProduct product) {
        RecentProductResource resource = new RecentProductResource();
        resource.setTitle(product.getTitle());
        resource.setDealUrl(product.getDealUrl());
        resource.setDescription(product.getDescription());
        resource.setImageUrl(product.getImageUrl());
        resource.setProductUrl(product.getProductUrl());
        resource.setPrice(product.getPrice());
        resource.setTemperature(product.getTemperature());
        resource.setSubmitTime(product.getSubmitTime());

        return resource;
    }
}
