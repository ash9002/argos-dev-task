package com.ashleymuswere.argos.repositories;

import com.ashleymuswere.argos.entities.RecentProduct;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Ashley on 07/02/2016.
 */
public interface RecentProductRepo extends CrudRepository<RecentProduct, String> {

    @Override
    RecentProduct save(RecentProduct product);

    @Override
    List<RecentProduct> findAll();

    RecentProduct findByTitle(String title);
}
