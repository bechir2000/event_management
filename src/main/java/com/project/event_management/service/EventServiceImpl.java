package com.project.event_management.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.event_management.entities.Event;
import com.project.event_management.entities.Organizer;
import com.project.event_management.exceptions.EventNotFoundException;
import com.project.event_management.exceptions.UserNotFoundException;
import com.project.event_management.repository.EventRepository;
import com.project.event_management.repository.OrganizerRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EventServiceImpl implements EventService{
    
    EventRepository eventRepository;
    OrganizerRepository organizerRepository;
    
    public Event getEvent(Long id) {
        Optional<Event> event= eventRepository.findById(id); //look for the event in the data base
        if(event.isPresent()){
            return event.get(); //return it if he exists
        }else {
            throw new EventNotFoundException(id);
        }
    }

    @Override
    public Event saveEvent(Event event, Long organizerId) {
        if(organizerRepository.findById(organizerId).isPresent()){ //look for organizer in the database
            Organizer organizer = organizerRepository.findById(organizerId).get(); //get the orgnizer if he exists
            event.setOrganizer(organizer); //set the event organizer
            return eventRepository.save(event); //save event with his organizer 
        }else {
            throw new UserNotFoundException("Organizer", organizerId);
        }
            
    }

    public Event updateEvent(Long id,String name,LocalDate date, LocalTime time, String location, String description, Integer maxNumOfAttendees){
        Optional<Event> oldEvent = eventRepository.findById(id); // look for the event in the database 
        if(oldEvent.isPresent()){ //if the event exists
            Event event= oldEvent.get(); //update
            event.setName(name);
            event.setDate(date);
            event.setTime(time);
            event.setLocation(location);
            event.setDescription(description);
            event.setMaxNumOfAttendees(maxNumOfAttendees);
            return eventRepository.save(event); // save updated event
        }else {
            throw new EventNotFoundException(id);
        }
    }

    @Override
    public void deleteEvent(Long id) {
        Optional<Event> event= eventRepository.findById(id); //look for event in the data base
        if(event.isPresent()){
            eventRepository.deleteById(id); //delete it if he exists
        }else {
            throw new  EventNotFoundException(id);
        }
    }

    @Override
    public List<Event> getEvents() {
        return (List<Event>) eventRepository.findAll();
    }

    public List<Event> getOrganizerEvents(Long organizerId) {
        Optional<Organizer> organizer= organizerRepository.findById(organizerId); //look for organizer in the database
        if (organizer.isPresent()){ //if he exists
            return (List<Event>) eventRepository.findByOrganizerId(organizerId); //return all the organizer events
        }else {
            throw new UserNotFoundException("Organizer", organizerId);
        }

    }

    
}
