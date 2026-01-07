package com.example.notification_service.service.impl;

import com.example.notification_service.model.Role;
import com.example.notification_service.model.User;
import com.example.notification_service.repository.UserRepository;
import com.example.notification_service.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String rawPassword) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (rawPassword == null || rawPassword.isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists");
        }

        String encoded = passwordEncoder.encode(rawPassword);

        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);

        // Use constructor or setters depending on your User entity
        User user = new User(
                null,          // id — let JPA generate it
                username,
                encoded,
                roles
        );

        return userRepository.save(user);
    }
}
