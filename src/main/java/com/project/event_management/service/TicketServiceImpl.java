package com.project.event_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.event_management.entities.Event;
import com.project.event_management.entities.Ticket;
import com.project.event_management.entities.User;
import com.project.event_management.exceptions.EventNotFoundException;
import com.project.event_management.exceptions.TicketNotFoundException;
import com.project.event_management.exceptions.UserNotFoundException;
import com.project.event_management.repository.EventRepository;
import com.project.event_management.repository.TicketRepository;
import com.project.event_management.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TicketServiceImpl implements TicketService{
    
    TicketRepository ticketRepository;
    UserRepository userRepository;
    EventRepository eventRepository;
      
    @Override
    public Ticket getTicket(Long eventId, Long userId) {
        Optional<Ticket> ticket = ticketRepository.findByEventIdAndUserId(eventId, userId); //look for the ticket by event and user ids
        if (ticket.isPresent()){
            return ticket.get();// if he exists return it
        }else {
            throw new TicketNotFoundException(eventId, userId);
        }
        
    }

    @Override
    public Ticket saveTicket(Ticket ticket, Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId).get(); //look for the event
        User user= userRepository.findById(userId).get();// look for the user
        ticket.setUser(user); // adding the user to our ticket
        ticket.setEvent(event); // adding the event to our ticket

        return ticketRepository.save(ticket); // save the ticket
    }

    @Override
    public void deleteTicket(Long eventId, Long userId) {
        Optional<Ticket> ticket = ticketRepository.findByEventIdAndUserId(eventId, userId); //look for the ticket by event and user ids
        if (ticket.isPresent()){
            ticketRepository.deleteByEventIdAndUserId(eventId, userId);// if the ticket exists delete it
        }else {
            throw new TicketNotFoundException(eventId, userId);
        }
    }

    @Override
    public void deleteTicket(Long id){
        Optional<Ticket> ticket = ticketRepository.findById(id); //look for the ticket by its id
        if (ticket.isPresent()){
            ticketRepository.deleteById(id);// if it exists delete it
        }else {
            throw new TicketNotFoundException(id);
        }
    }

    @Override
    public List<Ticket> getTickets() {
        return (List<Ticket>)ticketRepository.findAll();
    }

    @Override
    public List<Ticket> getUserTickets(Long userId) {
        Optional<User> user= userRepository.findById(userId); //look for the user in database
        if (user.isPresent()){ //if he exists
            return ticketRepository.findByUserId(userId); //get all user tickets
        }else {
            throw new UserNotFoundException("User", userId);
        }
    }

    @Override
    public List<Ticket> getEventTickets(Long eventId) {
        Optional<Event> event = eventRepository.findById(eventId); //look for the event in databse
        if(event.isPresent()){ //if the events exists
            return ticketRepository.findByEventId(eventId); //get all event tickets
        }else {
            throw new EventNotFoundException(eventId);
        }
    }
    
}
