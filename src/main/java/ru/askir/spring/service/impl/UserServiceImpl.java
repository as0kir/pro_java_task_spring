package ru.askir.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.askir.spring.dao.UserDao;
import ru.askir.spring.dto.User;
import ru.askir.spring.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    public final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User create(String username) {
        return userDao.create(username);
    }

    @Override
    public User update(Long id, String username) {
        return userDao.update(new User(id, username));
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
