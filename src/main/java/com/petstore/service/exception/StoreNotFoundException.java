package com.petstore.service.exception;

public class StoreNotFoundException extends Exception {

    public StoreNotFoundException() {
        super();
    }

    public StoreNotFoundException(String message) {
        super(message);
    }

    public StoreNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StoreNotFoundException(Throwable cause) {
        super(cause);
    }

    protected StoreNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
