package ru.askir.spring.payment.service.impl;

import org.springframework.stereotype.Service;
import ru.askir.spring.payment.dto.request.ProductRequest;
import ru.askir.spring.payment.dto.response.PaymentResponse;
import ru.askir.spring.payment.dto.response.ProductResponse;
import ru.askir.spring.payment.exception.ProductServiceException;
import ru.askir.spring.payment.service.PaymentService;
import ru.askir.spring.payment.service.ProductService;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final ProductService productService;

    public PaymentServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public PaymentResponse execute(Long productId, BigDecimal amount) {

        ProductResponse product = productService.getProductById(productId);

        if(Objects.isNull(product.balance()) || product.balance().compareTo(amount) < 0) {
            throw new ProductServiceException("Остаток на счете меньше суммы платежа");
        }

        productService.putProduct(
                new ProductRequest(
                        product.id(),
                        product.user().id(),
                        product.account(),
                        product.balance().subtract(amount),
                        product.typeAccount()));

        return new PaymentResponse();
    }
}
