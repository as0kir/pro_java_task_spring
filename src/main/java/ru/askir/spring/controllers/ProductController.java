package ru.askir.spring.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.askir.spring.dto.Product;
import ru.askir.spring.dto.request.ProductRequest;
import ru.askir.spring.service.ProductService;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    ResponseEntity<Product> createProduct(ProductRequest productRequest) {
        Product product = productService.create(productRequest);
        return ResponseEntity.ok(product);
    }

    @PutMapping
    ResponseEntity<Product> updateProduct(ProductRequest productRequest) {
        Product product = productService.update(productRequest);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping
    ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/byUserId/{userId}")
    ResponseEntity<List<Product>> getProductsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(productService.getProductsByUserId(userId));
    }

    @GetMapping("/byProductId/{productId}")
    ResponseEntity<Product> getProductById(@PathVariable Long productId){
        return ResponseEntity.ok(productService.getProductById(productId));
    }
}
