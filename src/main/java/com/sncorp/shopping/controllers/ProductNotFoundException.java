package com.sncorp.shopping.controllers;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        this(0);
    }

    public ProductNotFoundException(Integer id) {
        super("Product not found with id=" + id);
    }
}
