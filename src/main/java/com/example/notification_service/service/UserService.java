package com.example.notification_service.service;

import com.example.notification_service.model.User;

public interface UserService {
    User register(String username, String rawPassword);
}
