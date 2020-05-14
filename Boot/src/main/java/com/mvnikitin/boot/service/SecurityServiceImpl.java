package com.mvnikitin.boot.service;

import com.mvnikitin.boot.dao.RoleRepository;
import com.mvnikitin.boot.dao.UserRepository;
import com.mvnikitin.boot.entity.Role;
import com.mvnikitin.boot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SecurityServiceImpl implements SecurityService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("Invalid username or password"));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Invalid username or password"));
        return new
                org.springframework.security.core.userdetails.User(
                        user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    @Transactional
    private Collection<? extends GrantedAuthority>
    mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new
                SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
