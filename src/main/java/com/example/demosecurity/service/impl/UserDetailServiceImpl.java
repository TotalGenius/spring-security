package com.example.demosecurity.service.impl;

import com.example.demosecurity.entity.User;
import com.example.demosecurity.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserService userService;

    public UserDetailServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userService.getByUserName(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("user is not found");
        }
        return optionalUser.get();
    }
}
