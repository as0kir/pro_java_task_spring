package ru.askir.spring.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.askir.spring.payment.dto.response.ProductListResponse;
import ru.askir.spring.payment.dto.response.ProductResponse;
import ru.askir.spring.payment.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
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
