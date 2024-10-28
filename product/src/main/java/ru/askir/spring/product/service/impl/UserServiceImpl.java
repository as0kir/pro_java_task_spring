package ru.askir.spring.product.service.impl;

import org.springframework.stereotype.Service;
import ru.askir.spring.product.dto.response.UserResponse;
import ru.askir.spring.product.entity.User;
import ru.askir.spring.product.mapper.UserMapper;
import ru.askir.spring.product.repository.UserRepository;
import ru.askir.spring.product.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse create(String username) {
        User user = userRepository.save(new User(null, username));
        return userMapper.toDto(user);
    }

    @Override
    public UserResponse update(Long id, String username) {
        User user = userRepository.save(new User(id, username));
        return userMapper.toDto(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse findById(Long id) {
        return userMapper.toDto( userRepository.getReferenceById(id));
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
}
