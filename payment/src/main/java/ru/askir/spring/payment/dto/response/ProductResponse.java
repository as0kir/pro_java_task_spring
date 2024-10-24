package ru.askir.spring.payment.dto.response;

import ru.askir.spring.payment.dto.TypeAccount;

import java.math.BigDecimal;

public record ProductResponse(Long id, UserResponse user, String account, BigDecimal balance, TypeAccount typeAccount) {
}
