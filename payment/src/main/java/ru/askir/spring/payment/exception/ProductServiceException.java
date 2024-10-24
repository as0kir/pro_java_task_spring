package ru.askir.spring.payment.exception;

public class ProductServiceException extends RuntimeException{

    public ProductServiceException(String message) {
        super(message);
    }

    public ProductServiceException(Throwable cause) {
        super(cause);
    }
}
