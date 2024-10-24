package ru.askir.spring.product.exception;

public class ProductServiceException extends RuntimeException{
    public ProductServiceException(Exception ex) {
        super(ex);
    }

    public ProductServiceException(String message) {
        super(message);
    }
}
