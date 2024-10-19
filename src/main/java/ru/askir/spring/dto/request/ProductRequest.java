package ru.askir.spring.dto.request;

import ru.askir.spring.dto.TypeAccount;

import java.math.BigDecimal;

public record ProductRequest(Long id, Long user_id, String account, BigDecimal balance, TypeAccount typeAccount) {
}
