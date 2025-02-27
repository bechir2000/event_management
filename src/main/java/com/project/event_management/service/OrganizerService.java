package com.project.event_management.service;

import java.util.List;

import com.project.event_management.entities.Organizer;

public interface  OrganizerService {
    Organizer getOrganizer(Long id);
    Organizer saveOrganizer(Organizer organizer); 
    void deleteOrganizer(Long id);
    Organizer updateOrganizer(Long id,String fName, String lName,String phoneNumber, String password);
    List<Organizer> getOrganizers();
}
