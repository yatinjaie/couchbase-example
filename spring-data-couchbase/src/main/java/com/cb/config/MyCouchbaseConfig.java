package com.cb.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

@Configuration
@EnableCouchbaseRepositories(basePackages = { "com.cb.repository" })
public class MyCouchbaseConfig extends AbstractCouchbaseConfiguration {

    @Value("${couchbase.connectionstring}")
    private String connectionstring;

    @Value("${couchbase.bucket}")
    private String productBucket;

    @Value("${couchbase.username}")
    private String username;

    @Value("${couchbase.password}")
    private String password;

    @Override
    public String getConnectionString() {
        return connectionstring;
    }

    @Override
    public String getUserName() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getBucketName() {
        return productBucket;
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }
    //    @Bean
    //    public LocalValidatorFactoryBean localValidatorFactoryBean() {
    //        return new LocalValidatorFactoryBean();
    //    }
    //
    //    @Bean
    //    public ValidatingCouchbaseEventListener validatingCouchbaseEventListener() {
    //        return new ValidatingCouchbaseEventListener(localValidatorFactoryBean());
    //    }
}