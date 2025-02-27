package com.project.event_management.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.event_management.entities.User;
import com.project.event_management.exceptions.UserNotFoundException;
import com.project.event_management.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    
    @Override
    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email); //look for user by email
        if (user.isPresent()){
            return user.get(); //if he exists return it
        }else {
            throw new UserNotFoundException("user",00L);
        }
    }
}
