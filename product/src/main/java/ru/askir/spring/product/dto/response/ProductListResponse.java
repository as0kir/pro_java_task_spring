package ru.askir.spring.product.dto.response;

import java.util.List;

public record ProductListResponse(List<ProductResponse> productList) {
}
