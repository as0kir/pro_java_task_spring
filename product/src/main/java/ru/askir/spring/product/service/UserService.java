package ru.askir.spring.product.service;

import ru.askir.spring.product.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse create(String username);
    UserResponse update(Long id, String username);
    void delete(Long id);
    UserResponse findById(Long id);
    List<UserResponse> findAll();
}
