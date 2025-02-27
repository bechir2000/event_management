package com.project.event_management.entities;

public enum Role {
    ADMIN("ROLE_ADMIN"),
    ORGANIZER("ROLE_ORGANIZER"),
    CLIENT("ROLE_CLIENT"),
    ALL_PERMISSIONS_AUTHORITY("ROLE_ALL_PERMISSIONS");

    private final String authority;

    Role(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}
