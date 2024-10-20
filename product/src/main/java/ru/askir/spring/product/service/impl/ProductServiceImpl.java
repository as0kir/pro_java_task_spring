package ru.askir.spring.product.service.impl;

import org.springframework.stereotype.Service;
import ru.askir.spring.product.dao.ProductDao;
import ru.askir.spring.product.dto.response.ProductListResponse;
import ru.askir.spring.product.dto.response.ProductResponse;
import ru.askir.spring.product.dto.response.UserResponse;
import ru.askir.spring.product.dto.request.ProductRequest;
import ru.askir.spring.product.service.ProductService;
import ru.askir.spring.product.service.UserService;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;
    private final UserService userService;

    public ProductServiceImpl(ProductDao productDao, UserService userService) {
        this.productDao = productDao;
        this.userService = userService;
    }

    @Override
    public ProductResponse create(ProductRequest productRequest) {
        UserResponse user = userService.findById(productRequest.userId());
        return productDao.create(user, productRequest);
    }

    @Override
    public ProductResponse update(ProductRequest productRequest) {
        UserResponse user = userService.findById(productRequest.userId());
        return productDao.update(user, productRequest);
    }

    @Override
    public void delete(Long id) {
        productDao.delete(id);
    }

    @Override
    public ProductListResponse getProductsByUserId(Long userId) {
        return new ProductListResponse(productDao.findByUserId(userId));
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        return productDao.findById(productId);
    }

    @Override
    public ProductListResponse findAll() {
        return new ProductListResponse(productDao.findAll());
    }
}
