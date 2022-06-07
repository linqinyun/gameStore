package com.lin.gamestore.exceptions;

public class ProductCategoryOperationException extends RuntimeException {
    private static final long serialVersionUID = -617184992170969176L;

    public ProductCategoryOperationException(String message) {
        super(message);
    }
}
