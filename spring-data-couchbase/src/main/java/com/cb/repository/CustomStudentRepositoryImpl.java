//package com.cb.repository;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.couchbase.core.CouchbaseTemplate;
//
//import com.cb.model.Student;
//
//public class CustomStudentRepositoryImpl implements CustomStudentRepository {
//
//    private static final String DESIGN_DOC = "student";
//
//    @Autowired
//    private CouchbaseTemplate template;
//
//    @Override
//    public List<Student> findByFirstNameStartsWith(String s) {
//        return null;
//
////        return template.findByView(ViewQuery.from(DESIGN_DOC, "byFirstName").startKey(s).stale(Stale.FALSE),
//        //                Student.class);
//    }
//}