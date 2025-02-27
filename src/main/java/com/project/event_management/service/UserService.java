package com.project.event_management.service;

import com.project.event_management.entities.User;

public interface UserService {
    User getUserByEmail(String email);
}
