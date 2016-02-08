package com.ashleymuswere.argos.repositories;

import com.ashleymuswere.argos.entities.Product;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ashley on 03/02/2016.
 */

/**
 * This interface extends Spring Data JPA's CrudRepository, which implements various
 * DAO (CRUD) methods such as finding all entities, persisting an entity or finding an entity by a certain property.
 */

@Repository
public interface ProductRepo extends CrudRepository<Product, String> {

    @Override
    Product save(Product product);

    @Override
    List<Product> findAll();

    Product findByTitle(String title);
}
