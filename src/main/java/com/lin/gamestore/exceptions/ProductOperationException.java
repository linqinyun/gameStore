package com.lin.gamestore.exceptions;

public class ProductOperationException extends RuntimeException{
    private static final long serialVersionUID = -1226557151026016681L;

    public ProductOperationException(String message) {
        super(message);
    }
}
