package com.project.event_management.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.event_management.entities.Event;


public interface EventRepository extends CrudRepository<Event, Long>{
    List<Event> findByOrganizerId(Long organizerId);   
}
