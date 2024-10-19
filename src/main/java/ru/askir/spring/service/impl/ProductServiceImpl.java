package ru.askir.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.askir.spring.dao.ProductDao;
import ru.askir.spring.dto.Product;
import ru.askir.spring.dto.User;
import ru.askir.spring.dto.request.ProductRequest;
import ru.askir.spring.service.ProductService;
import ru.askir.spring.service.UserService;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    ProductDao productDao;
    UserService userService;

    public ProductServiceImpl(ProductDao productDao, UserService userService) {
        this.productDao = productDao;
        this.userService = userService;
    }

    @Override
    public Product create(ProductRequest productRequest) {
        User user = userService.findById(productRequest.user_id());
        return productDao.create(user, productRequest);
    }

    @Override
    public Product update(ProductRequest productRequest) {
        User user = userService.findById(productRequest.user_id());
        return productDao.update(user, productRequest);
    }

    @Override
    public void delete(Long id) {
        productDao.delete(id);
    }

    @Override
    public List<Product> getProductsByUserId(Long userId) {
        return productDao.findByUserId(userId);
    }

    @Override
    public Product getProductById(Long productId) {
        return productDao.findById(productId);
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }
}
