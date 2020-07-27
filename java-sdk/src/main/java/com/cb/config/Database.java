
package com.cb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;

@Configuration
public class Database {

    @Value("${storage.host}")
    private String host;

    @Value("${product.bucket}")
    private String productBucket;

    @Value("${review.bucket}")
    private String reviewBucket;

    @Value("${storage.username}")
    private String username;

    @Value("${storage.password}")
    private String password;

    //    @Value("${storage.clientorg.bucket}")
    //    private String clientOrgBucket;
    //
    //    @Value("${storage.clientorg.scope}")
    //    private String clientOrgScope;

    public @Bean Cluster loginCluster() {
        return Cluster.connect(host, username, password);
    }

    public @Bean Bucket productBucket() {
        return loginCluster().bucket(productBucket);
    }

    public @Bean Bucket reviewBucket() {
        return loginCluster().bucket(reviewBucket);
    }

    //    public Bucket clientOrgBucket() {
    //        return loginCluster().bucket(clientOrgBucket);
    //    }
    //
    //    public @Bean Scope clientOrgScope() {
    //        return clientOrgBucket().scope(clientOrgScope);
    //    }

}
