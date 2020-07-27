package com.cb.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cb.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {

    List<Product> findByName(String firstName);

}