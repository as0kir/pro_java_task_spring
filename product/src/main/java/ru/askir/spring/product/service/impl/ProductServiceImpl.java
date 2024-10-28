package ru.askir.spring.product.service.impl;

import org.springframework.stereotype.Service;
import ru.askir.spring.product.dto.response.ProductListResponse;
import ru.askir.spring.product.dto.response.ProductResponse;
import ru.askir.spring.product.dto.request.ProductRequest;
import ru.askir.spring.product.entity.Product;
import ru.askir.spring.product.mapper.ProductMapper;
import ru.askir.spring.product.repository.ProductRepository;
import ru.askir.spring.product.repository.UserRepository;
import ru.askir.spring.product.service.ProductService;

import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponse create(ProductRequest productRequest) {
        Product product = productRepository.save(productMapper.toEntity(productRequest, userRepository));
        return productMapper.toDto(product);
    }

    @Override
    public ProductResponse update(ProductRequest productRequest) {
        Product product = productRepository.save(productMapper.toEntity(productRequest, userRepository));
        return productMapper.toDto(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductListResponse getProductsByUserId(Long userId) {
        return new ProductListResponse(
                productRepository.findByUserId(userId)
                        .stream()
                        .map(productMapper::toDto)
                        .collect(Collectors.toList()));
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        return productMapper.toDto( productRepository.getReferenceById(productId));
    }

    @Override
    public ProductListResponse findAll() {
        return new ProductListResponse(
                productRepository
                    .findAll()
                    .stream()
                    .map(productMapper::toDto)
                    .collect(Collectors.toList()));
    }
}
