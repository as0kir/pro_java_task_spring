package ru.askir.spring.service;

import ru.askir.spring.dto.User;

import java.util.List;

public interface UserService {
    User create(String username);
    User update(User user);
    void delete(User user);
    User findById(int user);
    List<User> findAll();
}
