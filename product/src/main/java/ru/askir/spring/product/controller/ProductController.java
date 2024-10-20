package ru.askir.spring.product.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.askir.spring.product.dto.response.ProductListResponse;
import ru.askir.spring.product.dto.response.ProductResponse;
import ru.askir.spring.product.dto.request.ProductRequest;
import ru.askir.spring.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.create(productRequest);
        return ResponseEntity.ok(productResponse);
    }

    @PutMapping("/")
    ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.update(productRequest);
        return ResponseEntity.ok(productResponse);
    }

    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping
    ResponseEntity<ProductListResponse> getProducts(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/byUserId/{userId}")
    ResponseEntity<ProductListResponse> getProductsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(productService.getProductsByUserId(userId));
    }

    @GetMapping("/byProductId/{productId}")
    ResponseEntity<ProductResponse> getProductById(@PathVariable Long productId){
        return ResponseEntity.ok(productService.getProductById(productId));
    }
}
