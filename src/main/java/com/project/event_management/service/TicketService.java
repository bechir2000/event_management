package com.project.event_management.service;

import java.util.List;

import com.project.event_management.entities.Ticket;

public interface TicketService {
    Ticket getTicket(Long eventId, Long userId);
    Ticket saveTicket(Ticket ticket, Long eventId, Long userId);
    void deleteTicket(Long eventId, Long userId);
    void deleteTicket(Long id);
    List<Ticket> getUserTickets(Long userId);
    List<Ticket> getEventTickets(Long eventId);
    List<Ticket> getTickets();
}
