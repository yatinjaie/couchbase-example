package com.cb.dto;

import lombok.Data;

@Data
public class PopularProductsDTO {

    String name;

    int reviewCount;

    double avgRating;

    String color;

}
