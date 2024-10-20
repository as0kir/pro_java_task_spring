package ru.askir.spring.payment.dto.response;

import java.util.List;

public record ProductListResponse(List<ProductResponse> productList) {
}
