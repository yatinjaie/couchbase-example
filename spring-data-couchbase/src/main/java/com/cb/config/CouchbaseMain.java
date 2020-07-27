//package com.cb.config;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.couchbase.core.CouchbaseTemplate;
//
//import com.couchbase.client.java.Cluster;
//import com.couchbase.client.java.manager.query.CreatePrimaryQueryIndexOptions;
//
//import example.springdata.couchbase.model.Airline;
//import lombok.RequiredArgsConstructor;
//
///**
// * Main Class of this module
// *
// * @author Denis Rosa
// */
//@SpringBootApplication
//@RequiredArgsConstructor
//
//public class CouchbaseMain {
//
//    @Autowired
//    private final CouchbaseTemplate couchbaseTemplate;
//
//    @Autowired
//    private Cluster cluster;
//
//    /**
//     * Add the _class field to all Airline documents
//     */
//    @PostConstruct
//    private void postConstruct() {
//        cluster.queryIndexes().createPrimaryIndex(couchbaseTemplate.getBucketName(),
//                CreatePrimaryQueryIndexOptions.createPrimaryQueryIndexOptions().ignoreIfExists(true));
//
//        // Need to post-process travel data to add _class attribute
//        cluster.query("update `travel-sample` set _class='" + Airline.class.getName() + "' where type = 'airline'");
//
//    }
//}