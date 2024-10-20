package ru.askir.spring.product.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.askir.spring.product.dto.response.UserResponse;
import ru.askir.spring.product.service.UserService;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    ResponseEntity<UserResponse> createUser(String username) {
        UserResponse user = userService.create(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/user/{id}")
    ResponseEntity<UserResponse> updateUser(@PathVariable Long id, String username) {
        UserResponse user = userService.update(id, username);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/user/{id}")
    void deleteProduct(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/user/{id}")
    ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/user")
    ResponseEntity<List<UserResponse>> getUsers(){
        return ResponseEntity.ok(userService.findAll());
    }
}
