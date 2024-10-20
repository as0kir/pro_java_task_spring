package ru.askir.spring.service;

import ru.askir.spring.dto.Product;
import ru.askir.spring.dto.request.ProductRequest;
import java.util.List;

public interface ProductService {
    Product create(ProductRequest productRequest);
    Product update(ProductRequest productRequest);
    void delete(Long id);
    List<Product> getProductsByUserId(Long userId);
    Product getProductById(Long productId);
    List<Product> findAll();
}
