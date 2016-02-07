package com.ashleymuswere.argos.resourceasm;

import com.ashleymuswere.argos.controllers.ProductController;
import com.ashleymuswere.argos.entities.Product;
import com.ashleymuswere.argos.resources.ProductResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by Ashley on 03/02/2016.
 */
public class ProductResourceAssembler extends ResourceAssemblerSupport<Product, ProductResource> {

    public ProductResourceAssembler() {
        super(ProductController.class, ProductResource.class);
    }

    @Override
    public ProductResource toResource(Product product) {
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
