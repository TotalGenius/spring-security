package com.example.demosecurity.service.impl;

import com.example.demosecurity.entity.User;
import com.example.demosecurity.repository.UserRepository;
import com.example.demosecurity.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User get(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(user);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    public Optional<User> getByUserName(String userName) {
        return userRepository.findUserByUsername(userName);
    }
}
