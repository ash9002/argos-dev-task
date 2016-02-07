package com.ashleymuswere.argos.services;

import com.ashleymuswere.argos.entities.RecentProduct;
import com.ashleymuswere.argos.repositories.RecentProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ashley on 07/02/2016.
 */

@Service
public class RecentProductService {
    @Autowired
    private RecentProductRepo productRepo;

    public RecentProduct createProduct(RecentProduct data) {
        return productRepo.save(data);
    }

    public List<RecentProduct> findAllProducts() {
        List<RecentProduct> products;
        products = productRepo.findAll();
        return products;
    }

    public RecentProduct findByTitle(String title) {
        return productRepo.findByTitle(title);
    }
}
