package ru.askir.spring.dao;

import ru.askir.spring.dto.User;

import java.util.List;

public interface UserDao {
    User create(String username);
    User update(User user);
    void delete(User user);
    User findById(int user);
    List<User> findAll();
}
