package ru.askir.spring.product.mapper;

import org.mapstruct.Mapper;
import ru.askir.spring.product.dto.response.UserResponse;
import ru.askir.spring.product.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toDto(User user);
}
