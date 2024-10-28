package ru.askir.spring.product.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.askir.spring.product.dto.request.ProductRequest;
import ru.askir.spring.product.dto.response.ProductResponse;
import ru.askir.spring.product.entity.Product;
import ru.askir.spring.product.entity.User;
import ru.askir.spring.product.repository.UserRepository;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse toDto(Product product);

    @Mapping(target = "user", source = ".")
    Product toEntity(ProductRequest productRequest, @Context UserRepository userService);

    default User getUser(ProductRequest productRequest, @Context UserRepository userRepository) {
        return userRepository.getReferenceById(productRequest.userId());
    }
}
