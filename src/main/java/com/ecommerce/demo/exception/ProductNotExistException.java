package com.ecommerce.demo.exception;

public class ProductNotExistException extends IllegalArgumentException {
    public ProductNotExistException(String s) {
        super(s);
    }
}
