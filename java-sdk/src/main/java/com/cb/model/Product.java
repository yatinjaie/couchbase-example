package com.cb.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 6347882971487359328L;

    String productId;

    String color;

    double unitPrice;

    String[] reviewList;

    String imageURL;

    String name;

    String description;

    Date dateModified;

    String[] categories;

    String type;

    Date dateAdded;

}
