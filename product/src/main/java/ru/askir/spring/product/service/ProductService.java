package ru.askir.spring.product.service;

import ru.askir.spring.product.dto.response.ProductResponse;
import ru.askir.spring.product.dto.request.ProductRequest;
import ru.askir.spring.product.dto.response.ProductListResponse;

public interface ProductService {
    ProductResponse create(ProductRequest productRequest);
    ProductResponse update(ProductRequest productRequest);
    void delete(Long id);
    ProductListResponse getProductsByUserId(Long userId);
    ProductResponse getProductById(Long productId);
    ProductListResponse findAll();
}
