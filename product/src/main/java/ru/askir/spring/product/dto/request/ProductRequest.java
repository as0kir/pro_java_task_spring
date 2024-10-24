package ru.askir.spring.product.dto.request;

import ru.askir.spring.product.dto.TypeAccount;

import java.math.BigDecimal;

public record ProductRequest(Long id, Long userId, String account, BigDecimal balance, TypeAccount typeAccount) {
}
