package com.cb.product;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.cb.model.Result;
import com.couchbase.client.core.error.QueryException;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.query.QueryOptions;
import com.couchbase.client.java.query.QueryResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private Cluster cluster;


    public Result<List<Map<String, Object>>> getProduct(int limit, int offset) {
        StringBuilder builder = new StringBuilder();
        builder.append(" SELECT * FROM product LIMIT ").append(limit).append(" OFFSET ").append(offset);
        // .append(" UNNEST product.categories AS cat WHERE LOWER(cat) = 'golf' LIMIT 10
        // OFFSET 0" );
        QueryResult result = null;
        String query = builder.toString();
        log.info(query);
        try {
            result = cluster.query(query);
        } catch (QueryException e) {
            log.warn("Query failed with exception: " + e);
            throw new DataRetrievalFailureException("Query error", e);
        }
        return Result.get(result, query);
    }

    public Result<List<Map<String, Object>>> getProductCount() {
        String query = "SELECT COUNT(*) FROM product";
        QueryResult result = null;
        log.info(query);
        try {
            result = cluster.query(query);
        } catch (QueryException e) {
            log.warn("Query failed with exception: " + e);
            throw new DataRetrievalFailureException("Query error", e);
        }
        return Result.get(result, query);
    }

    public Result<List<Map<String, Object>>> getProductsByCategory(String category, int limit, int offset) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM product UNNEST product.categories AS cat WHERE LOWER(cat) = $val LIMIT ")
        .append(limit).append(" OFFSET ").append(offset);
        // .append(" UNNEST product.categories AS cat WHERE LOWER(cat) = 'golf' LIMIT 10
        // OFFSET 0" );
        QueryResult result = null;
        String query = builder.toString();
        log.info(query);
        try {
            result = cluster.query(query, QueryOptions.queryOptions().raw("$val", category.toLowerCase()));
        } catch (QueryException e) {
            log.warn("Query failed with exception: " + e);
            throw new DataRetrievalFailureException("Query error", e);
        }
        return Result.get(result, query);
    }

    public Result<List<Map<String, Object>>> getProductsBySearch(String search, int limit, int offset) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM product WHERE LOWER(name) LIKE $val LIMIT ")
        .append(limit).append(" OFFSET ").append(offset);
        // .append(" UNNEST product.categories AS cat WHERE LOWER(cat) = 'golf' LIMIT 10
        // OFFSET 0" );
        QueryResult result = null;
        String query = builder.toString();
        log.info(query);
        try {
            result = cluster.query(query, QueryOptions.queryOptions().raw("$val", "%" + search.toLowerCase() + "%"));
        } catch (QueryException e) {
            log.warn("Query failed with exception: " + e);
            throw new DataRetrievalFailureException("Query error", e);
        }
        return Result.get(result, query);

    }

    public Result<List<Map<String, Object>>> getMostPopularProducts(String category, int limit) {
        StringBuilder builder = new StringBuilder();
        builder.append(
                "SELECT product.name, COUNT(reviews) AS reviewCount, ROUND(AVG(reviews.rating),1) AS avgRating, product.color"
                        + " FROM reviews JOIN product ON KEYS reviews.productId UNNEST product.categories AS category WHERE category = $val"
                        + "  GROUP BY category, product ORDER BY avgRating DESC LIMIT ")
        .append(limit);
        QueryResult result = null;
        String query = builder.toString();
        log.info(query);
        try {
            result = cluster.query(query, QueryOptions.queryOptions().raw("$val", category.toLowerCase()));
        } catch (QueryException e) {
            log.warn("Query failed with exception: " + e);
            throw new DataRetrievalFailureException("Query error", e);
        }
        return Result.get(result, query);
    }

    public Result<List<Map<String, Object>>> getSortedProducts(String sortField, int limit,
            int offset) {
        StringBuilder builder = new StringBuilder();
        builder.append(
                "SELECT product.name, product.unitPrice, product.dateAdded, SUM(items.count) AS unitsSold "
                        + " FROM purchases UNNEST purchases.lineItems AS items JOIN product ON KEYS items.product "
                        + " GROUP BY product ORDER BY $var1, unitsSold DESC LIMIT $var3 OFFSET $var4");
        QueryResult result = null;
        String query = builder.toString();
        log.info(query);
        try {
            result = cluster.query(query, QueryOptions.queryOptions().raw("$var1", sortField)
                    .raw("$var3", limit).raw("$var4", offset));
        } catch (QueryException e) {
            log.warn("Query failed with exception: " + e);
            throw new DataRetrievalFailureException("Query error", e);
        }
        return Result.get(result, query);
    }

    public Result<List<Map<String, Object>>> getTopSellingProducts(int limit) {
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
        return Result.get(result, query);
    }

    public Result<List<Map<String, Object>>> getTopRatedProducts(int limit) {
        StringBuilder builder = new StringBuilder();
        builder.append(
                "SELECT product.name, AVG(reviews.rating) AS avg_rating FROM reviews JOIN product ON KEYS reviews.productId "
                        + "GROUP BY product  ORDER BY avg_rating DESC LIMIT $var");
        QueryResult result = null;
        String query = builder.toString();
        log.info(query);
        try {
            result = cluster.query(query, QueryOptions.queryOptions().raw("$var", limit));
        } catch (QueryException e) {
            log.warn("Query failed with exception: " + e);
            throw new DataRetrievalFailureException("Query error", e);
        }
        return Result.get(result, query);
    }

}
