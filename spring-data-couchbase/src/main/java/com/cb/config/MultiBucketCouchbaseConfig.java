//package com.cb.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
//import org.springframework.data.couchbase.core.CouchbaseTemplate;
//import org.springframework.data.couchbase.core.mapping.event.ValidatingCouchbaseEventListener;
//import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
//import org.springframework.data.couchbase.repository.config.RepositoryOperationsMapping;
//import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
//
////import com.baeldung.spring.data.couchbase.model.Campus;
//import com.couchbase.client.java.Bucket;
//
//@Configuration
//@EnableCouchbaseRepositories(basePackages = { "com.baeldung.spring.data.couchbase2b" })
//public class MultiBucketCouchbaseConfig extends AbstractCouchbaseConfiguration {
//
//    @Value("${storage.host}")
//    private String host;
//
//    @Value("${product.bucket}")
//    private String productBucket;
//
//    @Value("${storage.username}")
//    private String username;
//
//    @Value("${storage.password}")
//    private String password;
//
//    @Override
//    public String getConnectionString() {
//        return host;
//    }
//
//    @Override
//    public String getUserName() {
//        return username;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getBucketName() {
//        return productBucket;
//    }
//
//    @Bean
//    public Bucket campusBucket() throws Exception {
//        return couchbaseCluster().openBucket("baeldung2", "");
//    }
//
//    @Bean(name = "campusTemplate")
//    public CouchbaseTemplate campusTemplate() throws Exception {
//        CouchbaseTemplate template = new CouchbaseTemplate(couchbaseClusterInfo(), campusBucket(),
//                mappingCouchbaseConverter(), translationService());
//        template.setDefaultConsistency(getDefaultConsistency());
//        return template;
//    }
//
//    @Override
//    public void configureRepositoryOperationsMapping(RepositoryOperationsMapping baseMapping) {
//        try {
//            baseMapping.mapEntity(Campus.class, campusTemplate());
//        } catch (Exception e) {
//            // custom Exception handling
//        }
//    }
//
//    @Bean
//    public LocalValidatorFactoryBean localValidatorFactoryBean() {
//        return new LocalValidatorFactoryBean();
//    }
//
//    @Bean
//    public ValidatingCouchbaseEventListener validatingCouchbaseEventListener() {
//        return new ValidatingCouchbaseEventListener(localValidatorFactoryBean());
//    }
//}