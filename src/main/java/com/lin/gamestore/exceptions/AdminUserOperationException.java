package com.lin.gamestore.exceptions;

public class AdminUserOperationException extends RuntimeException {

    private static final long serialVersionUID = 3310128950711774938L;

    public AdminUserOperationException(String message) {
        super(message);
    }

}
