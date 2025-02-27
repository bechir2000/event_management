package com.project.event_management.exceptions;

public class EventNotFoundException extends RuntimeException{
    public EventNotFoundException(Long id) {
        super("The event id '" + id + "' does not exist!");
    }
    
}
