package ru.askir.spring.product.dao;

import ru.askir.spring.product.dto.response.ProductResponse;
import ru.askir.spring.product.dto.response.UserResponse;
import ru.askir.spring.product.dto.request.ProductRequest;

import java.util.List;

public interface ProductDao {
    ProductResponse create(UserResponse user, ProductRequest productRequest);
    ProductResponse update(UserResponse user, ProductRequest productRequest);
    void delete(Long id);
    ProductResponse findById(Long id);
    List<ProductResponse> findByUserId(Long user_id);
    List<ProductResponse> findAll();
}
