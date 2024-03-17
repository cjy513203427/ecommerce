package com.ecommerce.demo.exception;

public class AuthenticationFailException  extends IllegalArgumentException{
    public AuthenticationFailException(String s) {
        super(s);
    }
}
