package com.cb.dto;

import com.cb.model.Customer;
import com.cb.model.Product;
import com.cb.model.Purchase;

import lombok.Data;

@Data
public class PurchaseOrderDTO {

    private Purchase purchases;

    private Product product;

    private Customer customer;
}
