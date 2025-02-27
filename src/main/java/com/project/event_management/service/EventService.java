package com.project.event_management.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


import com.project.event_management.entities.Event;

public interface EventService {
    Event getEvent(Long id);
    Event saveEvent(Event event, Long organizerId);
    void deleteEvent(Long id);
    Event updateEvent(Long id,String name,LocalDate date, LocalTime time, String location, String description, Integer maxNumOfAttendees);
    List<Event> getEvents();
    List<Event> getOrganizerEvents(Long organizerId);

}
