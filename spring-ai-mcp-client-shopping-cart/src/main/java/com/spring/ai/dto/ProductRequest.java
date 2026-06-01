package com.spring.ai.dto;

import lombok.Data;

@Data
public class ProductRequest {

    private String productName;

    private double price;

    private int quantity;
}