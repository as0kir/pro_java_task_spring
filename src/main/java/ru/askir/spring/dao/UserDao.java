package ru.askir.spring.dao;

import ru.askir.spring.dto.User;

import java.util.List;

public interface UserDao {
    User create(String username);
    User update(User user);
    void delete(Long id);
    User findById(Long id);
    List<User> findAll();
}
