package ru.askir.spring.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.askir.spring.dto.User;
import ru.askir.spring.service.UserService;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    ResponseEntity<User> createUser(String username) {
        User user = userService.create(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/user/{id}")
    ResponseEntity<User> updateUser(@PathVariable Long id, String username) {
        User user = userService.update(id, username);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/user/{id}")
    void deleteProduct(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/user/{id}")
    ResponseEntity<User> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/user")
    ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.findAll());
    }
}
