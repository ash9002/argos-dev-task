package com.ashleymuswere.argos.services;

import com.ashleymuswere.argos.entities.Product;
import com.ashleymuswere.argos.repositories.ProductRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ashley on 03/02/2016.
 */

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Product createProduct(Product data) {
        return productRepo.save(data);
    }

    public List<Product> findAllProducts() {
        List<Product> products;
        products = productRepo.findAll();
        return products;
    }

    public Product findByTitle(String title) {
        return productRepo.findByTitle(title);
    }
}
