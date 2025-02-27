package com.project.event_management.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.event_management.entities.Ticket;
import com.project.event_management.service.TicketService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/ticket")
@CrossOrigin(origins = "http://localhost:3000")
public class TicketController {
    
    TicketService ticketService;

    @GetMapping("/event/{eventId}/user/{userId}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long eventId, @PathVariable Long userId){
        return new ResponseEntity<>(ticketService.getTicket(eventId, userId), HttpStatus.OK);
    }
    
    @PostMapping("/event/{eventId}/user/{userId}")
    public ResponseEntity<Ticket> saveTicket(@Valid @RequestBody Ticket ticket, @PathVariable Long eventId, @PathVariable Long userId){
        return new ResponseEntity<>(ticketService.saveTicket(ticket, eventId, userId), HttpStatus.CREATED);
    }

    @DeleteMapping("/event/{eventId}/user/{userId}")
    public ResponseEntity<HttpStatus> deleteTicket(@PathVariable Long eventId, @PathVariable Long userId){
        ticketService.deleteTicket(eventId, userId);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTicket(@PathVariable Long id){
        ticketService.deleteTicket(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ticket>> getTickets(){
        return new ResponseEntity<>(ticketService.getTickets(), HttpStatus.OK);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Ticket>> getEventTickets(@PathVariable Long eventId){
                return new ResponseEntity<>(ticketService.getEventTickets(eventId), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Ticket>> getUserTickets(@PathVariable Long userId){
                return new ResponseEntity<>(ticketService.getUserTickets(userId), HttpStatus.OK);
    }
}
