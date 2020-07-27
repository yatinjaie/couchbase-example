package com.cb.dto;

import lombok.Data;

@Data
public class BigOrderDTO {

    private String purchaseId;

    private String product;

    private String name;

    long unitPrice;
}
