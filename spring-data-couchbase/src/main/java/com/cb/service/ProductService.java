package com.cb.service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cb.model.Product;
import com.cb.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;


    public Product findOne(String id) {
        Optional<Product> personOp = repo.findById(id);
        if (personOp.isPresent()) {
            return personOp.get();
        }
        return null;
    }


    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        Iterator<Product> it = repo.findAll().iterator();
        while (it.hasNext()) {
            list.add(it.next());
        }
        return list;
    }

    public Product createProduct(Product product) {
        return repo.save(product);
    }

}
