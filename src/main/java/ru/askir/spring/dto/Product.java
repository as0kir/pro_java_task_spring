package ru.askir.spring.dto;

import java.math.BigDecimal;

public record Product(Long id, User user, String account, BigDecimal balance, TypeAccount typeAccount) {
}
