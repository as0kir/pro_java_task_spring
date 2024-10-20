package ru.askir.spring.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.askir.spring.payment.dto.response.ErrorResponse;

@RestControllerAdvice
public class PaymentExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ProductServiceException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleProductServiceException(Exception ex, WebRequest request) {
        return new ResponseEntity(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
