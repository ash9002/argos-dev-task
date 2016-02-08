package com.ashleymuswere.argos.resourceasm;

import com.ashleymuswere.argos.controllers.ProductController;
import com.ashleymuswere.argos.entities.Product;
import com.ashleymuswere.argos.resources.ProductResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by Ashley on 03/02/2016.
 */

/**
 * This class is used to map an entity to its resource, which will be what is
 * exposed by the controllers as REST resources. This is done by
 * extending the ResourceAssemblerSupport class and passing in the entity to be
 * mapped and its corresponding resource class ( the one extending ResourceSupport )
 */

public class ProductResourceAssembler extends ResourceAssemblerSupport<Product, ProductResource> {

    public ProductResourceAssembler() {
        super(ProductController.class, ProductResource.class);
    }

    @Override //overriding to toResource. This method will take an entity an convert it into a resource
    public ProductResource toResource(Product product) {

        //setting properties for resource
        ProductResource resource = new ProductResource();
        resource.setTitle(product.getTitle());
        resource.setDealUrl(product.getDealUrl());
        resource.setDescription(product.getDescription());
        resource.setImageUrl(product.getImageUrl());
        resource.setProductUrl(product.getProductUrl());
        resource.setPrice(product.getPrice());
        resource.setTemperature(product.getTemperature());
        resource.setEbayPrice(product.getEbayPrice());
        resource.setEbayUrl(product.getEbayUrl());
        resource.setPriceDifference(product.getPriceDifference());

        return resource;
    }
}
