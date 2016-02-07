package com.ashleymuswere.argos.repositories;

import com.ashleymuswere.argos.entities.Product;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ashley on 03/02/2016.
 */

@Repository
public interface ProductRepo extends CrudRepository<Product, String> {

    @Override
    Product save(Product product);

    @Override
    List<Product> findAll();

    Product findByTitle(String title);
}
