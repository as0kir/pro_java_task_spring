package ru.askir.spring.product.service.impl;

import org.springframework.stereotype.Service;
import ru.askir.spring.product.dao.UserDao;
import ru.askir.spring.product.dto.response.UserResponse;
import ru.askir.spring.product.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserResponse create(String username) {
        return userDao.create(username);
    }

    @Override
    public UserResponse update(Long id, String username) {
        return userDao.update(new UserResponse(id, username));
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public UserResponse findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public List<UserResponse> findAll() {
        return userDao.findAll();
    }
}
