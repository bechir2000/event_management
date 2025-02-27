package com.project.event_management.exceptions;

public class TicketNotFoundException extends RuntimeException{
    
    public TicketNotFoundException(Long eventId, Long clientId) {
        super("The ticket with event id '" + eventId + "'and client id'"+ clientId + "' does not exist!");
    }

    public TicketNotFoundException(Long id) {
        super("The ticket id '" + id + "' does not exist!");
    }
    
}
