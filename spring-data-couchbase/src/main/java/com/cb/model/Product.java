package com.cb.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 6347882971487359328L;

    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    String productId;

    @Field
    String color;

    @Field
    double unitPrice;

    @Field
    String[] reviewList;

    @Field
    String imageURL;

    @Field
    String name;

    @Field
    String description;

    @Field
    Date dateModified;

    @Field
    String[] categories;

    @Field
    String type;

    @Field
    Date dateAdded;

}
