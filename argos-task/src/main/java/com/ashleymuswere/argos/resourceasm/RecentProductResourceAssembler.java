package com.ashleymuswere.argos.resourceasm;

import com.ashleymuswere.argos.controllers.RecentProductController;
import com.ashleymuswere.argos.entities.RecentProduct;
import com.ashleymuswere.argos.resources.RecentProductResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by Ashley on 07/02/2016.
 */

/**
 * This class is used to map an entity to its resource, which will be what is
 * exposed by the controllers as REST resources. This is done by
 * extending the ResourceAssemblerSupport class and passing in the entity to be
 * mapped and its corresponding resource class ( the one extending ResourceSupport )
 */

public class RecentProductResourceAssembler extends ResourceAssemblerSupport<RecentProduct, RecentProductResource> {

    public RecentProductResourceAssembler() {
        super(RecentProductController.class, RecentProductResource.class);
    }

    @Override //overriding to toResource. This method will take an entity an convert it into a resource
    public RecentProductResource toResource(RecentProduct product) {

        //setting properties for resource
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
