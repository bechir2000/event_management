package com.project.event_management.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String className, Long id) {
        super("The '"+ className +"' with id '" + id + "' does not exist!");
    }
}
