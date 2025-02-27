package com.project.event_management.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.event_management.entities.Organizer;

public interface OrganizerRepository extends CrudRepository<Organizer, Long>{
    
}
