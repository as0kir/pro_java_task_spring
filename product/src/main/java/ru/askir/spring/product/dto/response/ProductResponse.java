package ru.askir.spring.product.dto.response;

import ru.askir.spring.product.dto.TypeAccount;

import java.math.BigDecimal;

public record ProductResponse(Long id, UserResponse user, String account, BigDecimal balance, TypeAccount typeAccount) {
}
