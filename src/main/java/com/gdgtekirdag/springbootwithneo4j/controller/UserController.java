package com.gdgtekirdag.springbootwithneo4j.controller;

import java.util.List;
import java.util.UUID;

import com.gdgtekirdag.springbootwithneo4j.domain.User;
import com.gdgtekirdag.springbootwithneo4j.dto.UserDTO;
import com.gdgtekirdag.springbootwithneo4j.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = "application/json", path = "/api/v1/user")
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/{userId}")
    public User getByUserId(@PathVariable(value = "userId") UUID userId) {
        return userService.getById(userId);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList = userService.listAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User user = new User(userDTO.getUsername());
        User savedUser = userService.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<User> updateUser(@RequestBody UserDTO userDTO) {
        User user = userService.getByUsername(userDTO.getUsername());
        User updatedUser = userService.update(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "userId") UUID userId) {
        userService.delete(userId);
        return new ResponseEntity<>("User is deleted.", HttpStatus.OK);
    }
}
