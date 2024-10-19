package ru.askir.spring.dao;

import ru.askir.spring.dto.Product;
import ru.askir.spring.dto.User;
import ru.askir.spring.dto.request.ProductRequest;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDao {
    Product create(User user, ProductRequest productRequest);
    Product update(User user, ProductRequest productRequest);
    void delete(Long id);
    Product findById(Long id);
    List<Product> findByUserId(Long user_id);
    List<Product> findAll();
}
