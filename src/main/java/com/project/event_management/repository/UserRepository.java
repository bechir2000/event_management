package com.project.event_management.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.project.event_management.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
    Optional<User> findByEmail(String email);
    
}
