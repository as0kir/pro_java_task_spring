package ru.askir.spring.payment.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import ru.askir.spring.payment.dto.response.ErrorResponse;

import java.io.IOException;

@Component
public class ProductErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ErrorResponse errorResponse = mapper.readValue(response.getBody(), ErrorResponse.class);
        throw new ProductServiceException(errorResponse.message());
    }
}
