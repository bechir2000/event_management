package com.project.event_management.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.event_management.entities.Admin;

public interface AdminRepository extends CrudRepository<Admin, Long>{
    //CrudRepository provides all crud operations for our class
}
