package ru.askir.spring.payment.dto.request;

import ru.askir.spring.payment.dto.TypeAccount;

import java.math.BigDecimal;

public record ProductRequest(Long id, Long userId, String account, BigDecimal balance, TypeAccount typeAccount) {
}
