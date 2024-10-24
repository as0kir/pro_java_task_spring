package ru.askir.spring.payment.service;

import ru.askir.spring.payment.dto.response.ProductListResponse;
import ru.askir.spring.payment.dto.request.ProductRequest;
import ru.askir.spring.payment.dto.response.ProductResponse;

public interface ProductService {
    ProductListResponse getProductsByUserId(Long userId);
    ProductResponse getProductById(Long productId);

    void putProduct(ProductRequest productRequest);
}
