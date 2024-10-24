package ru.askir.spring.payment.service;

import ru.askir.spring.payment.dto.response.PaymentResponse;

import java.math.BigDecimal;

public interface PaymentService {
    PaymentResponse execute(Long productId, BigDecimal amount);
}
