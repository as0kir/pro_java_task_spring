package ru.askir.spring.service;

import ru.askir.spring.dto.User;

import java.util.List;

public interface UserService {
    User create(String username);
    User update(Long id, String username);
    void delete(Long id);
    User findById(Long id);
    List<User> findAll();
}
