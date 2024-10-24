package ru.askir.spring.payment.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.askir.spring.payment.dto.request.ProductRequest;
import ru.askir.spring.payment.dto.response.ProductListResponse;
import ru.askir.spring.payment.dto.response.ProductResponse;
import ru.askir.spring.payment.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private final RestTemplate productRestTemplate;

    public ProductServiceImpl(@Qualifier("productRestClient") RestTemplate productRestTemplate) {
        this.productRestTemplate = productRestTemplate;
    }

    @Override
    public ProductListResponse getProductsByUserId(Long userId) {
        return productRestTemplate.getForObject("/byUserId/{userId}", ProductListResponse.class, userId);
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        return productRestTemplate.getForObject("/byProductId/{productId}", ProductResponse.class, productId);
    }

    @Override
    public void putProduct(ProductRequest productRequest) {
        HttpEntity<ProductRequest> request = new HttpEntity<>(productRequest);
        //productRestTemplate.put("/", request);
        productRestTemplate.exchange("/", HttpMethod.PUT, request, ProductRequest.class);
    }
}
