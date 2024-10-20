package ru.askir.spring.product.dao;

import ru.askir.spring.product.dto.response.UserResponse;

import java.util.List;

public interface UserDao {
    UserResponse create(String username);
    UserResponse update(UserResponse user);
    void delete(Long id);
    UserResponse findById(Long id);
    List<UserResponse> findAll();
}
