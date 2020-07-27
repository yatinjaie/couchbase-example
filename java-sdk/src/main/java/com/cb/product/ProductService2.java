package com.cb.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.cb.dto.CountDTO;
import com.cb.dto.PopularProductsDTO;
import com.cb.dto.ProductRatingDTO;
import com.cb.dto.TopSellingProductsDTO;
import com.cb.model.Product;
import com.cb.model.Result;
import com.couchbase.client.core.error.QueryException;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.query.QueryOptions;
import com.couchbase.client.java.query.QueryResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService2 {

    @Autowired
    private Cluster cluster;

    public List<CountDTO> getProductCount() {
        String query = "SELECT COUNT(*) as count FROM product";
        QueryResult result = null;
        log.info(query);
        try {
            result = cluster.query(query);
            log.info(result.toString());
        } catch (QueryException e) {
            log.warn("Query failed with exception: " + e);
            throw new DataRetrievalFailureException("Query error", e);
        }
        return result.rowsAs(CountDTO.class);
    }

    public List<Product> getProduct(int limit, int offset) {
        StringBuilder builder = new StringBuilder();
        builder.append(" SELECT product.* FROM product LIMIT ").append(limit).append(" OFFSET ").append(offset);
        QueryResult result = null;
        String query = builder.toString();
        log.info(query);
        try {
            result = cluster.query(query);
        } catch (QueryException e) {
            log.warn("Query failed with exception: " + e);
            throw new DataRetrievalFailureException("Query error", e);
        }
        return result.rowsAs(Product.class);
    }

    public List<Product> getProductsByCategory(String category, int limit, int offset) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT product.* FROM product UNNEST product.categories AS cat WHERE LOWER(cat) = $val LIMIT ")
        .append(limit).append(" OFFSET ").append(offset);
        QueryResult result = null;
        String query = builder.toString();
        log.info(query);
        try {
            result = cluster.query(query, QueryOptions.queryOptions().raw("$val", category.toLowerCase()));
        } catch (QueryException e) {
            log.warn("Query failed with exception: " + e);
            throw new DataRetrievalFailureException("Query error", e);
        }
        return result.rowsAs(Product.class);
    }

    public List<Product> getProductsBySearch(String search, int limit, int offset) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT product.* FROM product WHERE LOWER(name) LIKE $val LIMIT ").append(limit)
        .append(" OFFSET ")
        .append(offset);
        QueryResult result = null;
        String query = builder.toString();
        log.info(query);
        try {
            result = cluster.query(query, QueryOptions.queryOptions().raw("$val", "%" + search.toLowerCase() + "%"));
        } catch (QueryException e) {
            log.warn("Query failed with exception: " + e);
            throw new DataRetrievalFailureException("Query error", e);
        }
        return result.rowsAs(Product.class);

    }

    public List<PopularProductsDTO> getMostPopularProducts(String category, int limit) {
        StringBuilder builder = new StringBuilder();
        builder.append(
                "SELECT product.name, COUNT(reviews) AS reviewCount, ROUND(AVG(reviews.rating),1) AS avgRating, product.color"
                        + " FROM reviews JOIN product ON KEYS reviews.productId UNNEST product.categories AS category WHERE LOWER(category) = $val"
                        + "  GROUP BY category, product ORDER BY avgRating DESC LIMIT ")
        .append(limit);
        QueryResult result = null;
        String query = builder.toString();
        log.info(query);
        try {
            result = cluster.query(query, QueryOptions.queryOptions().raw("$val", category.toLowerCase()));
            log.info(Result.get(result, query).toString());
        } catch (QueryException e) {
            log.warn("Query failed with exception: " + e);
            throw new DataRetrievalFailureException("Query error", e);
        }
        return result.rowsAs(PopularProductsDTO.class);
    }

    public List<Product> getSortedProducts(String sortField, int limit, int offset) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT product.name, product.unitPrice, product.dateAdded, SUM(items.count) AS unitsSold "
                + " FROM purchases UNNEST purchases.lineItems AS items JOIN product ON KEYS items.product "
                + " GROUP BY product ORDER BY $var1, unitsSold DESC LIMIT $var3 OFFSET $var4");
        QueryResult result = null;
        String query = builder.toString();
        log.info(query);
        try {
            result = cluster.query(query,
                    QueryOptions.queryOptions().raw("$var1", sortField).raw("$var3", limit).raw("$var4", offset));
        } catch (QueryException e) {
            log.warn("Query failed with exception: " + e);
            throw new DataRetrievalFailureException("Query error", e);
        }
        return result.rowsAs(Product.class);
    }

    public List<TopSellingProductsDTO> getTopSellingProducts(int limit) {
        StringBuilder builder = new StringBuilder();
        builder.append(
                " SELECT product.name, SUM(items.count) AS unitsSold FROM purchases UNNEST purchases.lineItems AS items"
                        + " JOIN product ON KEYS items.product GROUP BY product "
                        + " ORDER BY unitsSold DESC LIMIT $var");
        QueryResult result = null;
        String query = builder.toString();
        log.info(query);
        try {
            result = cluster.query(query, QueryOptions.queryOptions().raw("$var", limit));
        } catch (QueryException e) {
            log.warn("Query failed with exception: " + e);
            throw new DataRetrievalFailureException("Query error", e);
        }
        return result.rowsAs(TopSellingProductsDTO.class);
    }

    public List<ProductRatingDTO> getTopRatedProducts(int limit) {
        StringBuilder builder = new StringBuilder();
        builder.append(
                "SELECT product.name,product.productId, AVG(reviews.rating) AS avgRating FROM reviews JOIN product ON KEYS reviews.productId "
                        + "GROUP BY product  ORDER BY avgRating DESC LIMIT $var");
        QueryResult result = null;
        String query = builder.toString();
        log.info(query);
        try {
            result = cluster.query(query, QueryOptions.queryOptions().raw("$var", limit));
        } catch (QueryException e) {
            log.warn("Query failed with exception: " + e);
            throw new DataRetrievalFailureException("Query error", e);
        }
        return result.rowsAs(ProductRatingDTO.class);
    }

}
