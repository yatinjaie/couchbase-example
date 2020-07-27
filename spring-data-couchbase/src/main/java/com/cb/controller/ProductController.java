package com.cb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cb.model.Product;
import com.cb.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProductController {


    @Autowired
    private ProductService productService;

    @RequestMapping("/getProducts")
    public ResponseEntity<?> getProducts() {
        Object result = productService.findAll();
        log.info("result : " + result);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/createProduct")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        log.info("result : " + product);
        Object result = productService.createProduct(product);
        return ResponseEntity.ok(result);
    }




}
