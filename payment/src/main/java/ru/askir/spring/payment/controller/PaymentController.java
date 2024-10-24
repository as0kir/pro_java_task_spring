package ru.askir.spring.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.askir.spring.payment.dto.response.PaymentResponse;
import ru.askir.spring.payment.service.PaymentService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PutMapping("/execute")
    ResponseEntity<PaymentResponse> execute(Long productId, BigDecimal amount) {
        return ResponseEntity.ok(paymentService.execute(productId, amount));
    }
}
