package com.mvnikitin.boot.services;

import com.mvnikitin.boot.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    public User findByUsername(String username);
    public List<User> findAll();
}
