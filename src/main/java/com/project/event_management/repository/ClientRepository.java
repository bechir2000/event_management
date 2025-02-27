package com.project.event_management.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.event_management.entities.Client;
import java.util.Optional;


public interface ClientRepository extends CrudRepository<Client, Long>{
    Optional<Client> findByEmail(String email);
}
