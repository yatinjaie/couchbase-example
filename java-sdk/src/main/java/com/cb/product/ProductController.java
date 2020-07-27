package com.cb.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    //    @Autowired
    //    private ProductService productService;
    //
    @Autowired
    private ProductService2 productService;

    @RequestMapping("/getProductCount")
    public ResponseEntity<?> getProductCount() {
        Object result = productService.getProductCount();
        return ResponseEntity.ok(result);
    }

    @RequestMapping("/getProducts")
    public ResponseEntity<?> getProducts(@RequestParam int limit, @RequestParam int offset) {
        Object result = productService.getProduct(limit, offset);
        return ResponseEntity.ok(result);
    }

    @RequestMapping("/getProductsByCategory")
    public ResponseEntity<?> getProductsByCategory(@RequestParam String category,
            @RequestParam int limit, @RequestParam int offset) {
        Object result = productService.getProductsByCategory(category, limit, offset);
        return ResponseEntity.ok(result);
    }

    @RequestMapping("/getProductsBySearch")
    public ResponseEntity<?> getProductsBySearch(@RequestParam String search,
            @RequestParam int limit, @RequestParam int offset) {
        Object result = productService.getProductsBySearch(search, limit, offset);
        return ResponseEntity.ok(result);
    }

    @RequestMapping("/getMostPopularProducts")
    public ResponseEntity<?> getMostPopularProducts(@RequestParam String category,
            @RequestParam int limit) {
        Object result = productService.getMostPopularProducts(category, limit);
        return ResponseEntity.ok(result);
    }

    @RequestMapping("/getSortedProducts")
    public ResponseEntity<?> getSortedProducts(@RequestParam String sortField,
            @RequestParam int limit, @RequestParam int offset) {
        Object result = productService.getSortedProducts(sortField, limit,
                offset);
        return ResponseEntity.ok(result);
    }

    @RequestMapping("/getTopSellingProducts")
    public ResponseEntity<?> getTopSellingProducts(@RequestParam int limit) {
        Object result = productService.getTopSellingProducts(limit);
        return ResponseEntity.ok(result);
    }

    @RequestMapping("/getTopRatedProducts")
    public ResponseEntity<?> getTopRatedProducts(@RequestParam int limit) {
        Object result = productService.getTopRatedProducts(limit);
        return ResponseEntity.ok(result);
    }

}
