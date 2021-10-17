package com.gdgtekirdag.springbootwithneo4j.service;

import java.util.List;
import java.util.UUID;

import com.gdgtekirdag.springbootwithneo4j.domain.User;
import com.gdgtekirdag.springbootwithneo4j.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(UUID userId) {
        return userRepository.getById(userId);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public User save(User user) {
        User savedUser = userRepository.saveUser(user.getUsername());
        return savedUser;
    }

    @Override
    public User update(User user) {
        User savedUser = userRepository.updateUser(user.getUserId(), user.getUsername());
        return savedUser;
    }

    @Override
    public void delete(UUID userId) {
        userRepository.deleteUser(userId);
    }
}
