package com.gdgtekirdag.springbootwithneo4j.service;

import java.util.List;
import java.util.UUID;

import com.gdgtekirdag.springbootwithneo4j.domain.User;

public interface UserService {

    List<User> listAll();

    User getById(UUID userId);

    User getByUsername(String username);

    User save(User user);

    User update(User user);

    void delete(UUID userId);
}
