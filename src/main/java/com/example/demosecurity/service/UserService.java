package com.example.demosecurity.service;

import com.example.demosecurity.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();

    User get(Long id);

    void save(User user, String roleSelect);

    void delete(Long id);

    Optional<User> getByUserName(String userName);
}
