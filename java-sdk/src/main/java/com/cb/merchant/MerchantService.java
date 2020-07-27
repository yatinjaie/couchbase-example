package com.cb.merchant;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.cb.dto.BigOrderDTO;
import com.cb.dto.CountDTO;
import com.cb.dto.CustomerByRegionDTO;
import com.cb.dto.ProductRatingDTO;
import com.cb.dto.PurchaseOrderDTO;
import com.cb.dto.SalesDTO;
import com.cb.model.Result;
import com.couchbase.client.core.error.QueryException;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.query.QueryOptions;
import com.couchbase.client.java.query.QueryResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MerchantService {

    @Autowired
    private Cluster cluster;

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

    public List<PurchaseOrderDTO> getPurchaseOrder() {
        StringBuilder builder = new StringBuilder();
        builder.append(
                "SELECT purchases, product, customer FROM purchases USE KEYS 'purchase0' UNNEST purchases.lineItems AS items "
                        + "JOIN product ON KEYS items.product JOIN customer ON KEYS purchases.customerId");
        QueryResult result = null;
        String query = builder.toString();
        try {
            result = cluster.query(query);
        } catch (QueryException e) {
            log.warn("Query failed with exception: " + e);
            throw new DataRetrievalFailureException("Query error", e);
        }
        return result.rowsAs(PurchaseOrderDTO.class);
    }

    public List<CustomerByRegionDTO> getCustomersByRegion() {
        StringBuilder builder = new StringBuilder();
        builder.append(
                "SELECT COUNT(customer) AS customerCount, state FROM customer "
                        + "GROUP BY state ORDER BY customerCount DESC");
        QueryResult result = null;
        String query = builder.toString();
        try {
            result = cluster.query(query);
        } catch (QueryException e) {
            log.warn("Query failed with exception: " + e);
            throw new DataRetrievalFailureException("Query error", e);
        }
        return result.rowsAs(CustomerByRegionDTO.class);
    }

    public List<CountDTO> getActiveCustomersForDateRange(String fromDate, String toDate) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT COUNT(DISTINCT purchases.customerId) AS count FROM purchases "
                + "WHERE purchases.purchasedAt BETWEEN $fromDate AND $toDate");
        QueryResult result = null;
        String query = builder.toString();
        try {
            result = cluster.query(query,QueryOptions.queryOptions().raw("$fromDate", fromDate).raw("$toDate", toDate));
        } catch (QueryException e) {
            log.warn("Query failed with exception: " + e);
            throw new DataRetrievalFailureException("Query error", e);
        }
        return result.rowsAs(CountDTO.class);
    }

    public List<ProductRatingDTO> getLowRatedProducts() {
        StringBuilder builder = new StringBuilder();
        builder.append(
                "SELECT product.name, product.productId, ROUND(AVG(reviews.rating), 3) avgRating "
                        + "FROM product JOIN reviews ON KEYS product.reviewList "
                        + "GROUP BY product HAVING AVG(reviews.rating) < 1");
        QueryResult result = null;
        String query = builder.toString();
        try {
            result = cluster.query(query);
        } catch (QueryException e) {
            log.warn("Query failed with exception: " + e);
            throw new DataRetrievalFailureException("Query error", e);
        }
        return result.rowsAs(ProductRatingDTO.class);
    }

    public List<SalesDTO> getMonthWiseSales() {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT SUBSTR(purchases.purchasedAt, 0, 7) as month,"
                + " ROUND(SUM(product.unitPrice * items.count)/1000000, 3) revenueMillion"
                + " FROM purchases UNNEST purchases.lineItems AS items JOIN product ON KEYS items.product"
                + " GROUP BY SUBSTR(purchases.purchasedAt, 0, 7) ORDER BY month "
                );
        QueryResult result = null;
        String query = builder.toString();
        try {
            result = cluster.query(query);
        } catch (QueryException e) {
            log.warn("Query failed with exception: " + e);
            throw new DataRetrievalFailureException("Query error", e);
        }
        return result.rowsAs(SalesDTO.class);
    }

    public List<BigOrderDTO> getBigTicketOrdersForMonth(int month, int year) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT purchases.purchaseId, l.product, prod.name, prod.unitPrice"
                +" FROM purchases UNNEST purchases.lineItems l"
                + " JOIN product prod ON KEYS l.product"
                + " WHERE DATE_PART_STR(purchases.purchasedAt,'month') = $month"
                + " AND DATE_PART_STR(purchases.purchasedAt,'year') = $year" + " AND prod.unitPrice > 500"
                );
        QueryResult result = null;
        String query = builder.toString();
        try {
            result = cluster.query(query, QueryOptions.queryOptions().raw("$month", month).raw("$year", year));
        } catch (QueryException e) {
            log.warn("Query failed with exception: " + e);
            throw new DataRetrievalFailureException("Query error", e);
        }
        return result.rowsAs(BigOrderDTO.class);
    }

}
