package com.project.event_management.security;

public class SecurityConstants {
    public static final String SECRET_KEY = "aQeThWmZq8t1z?z#C&F)J@NcRfUjX@2r5u8x/A!D*G-QaPdSgVkYp7s2v8y$B&E)"; //secret key
    public static final int TOKEN_EXPIRATION = 86400000; // 86400000 milliseconds = 24 hours.
    public static final String BEARER = "Bearer "; // Authorization : "Bearer " + Token 
    public static final String AUTHORIZATION = "Authorization"; // "Authorization" : Bearer Token
    public static final String CLIENT_REGISTER_PATH = "/client/register"; // Public path that clients can use to register.
    public static final String ORGANIZER_REGISTER_PATH = "/organizer/register";// Public path that organizers can use to register.
    public static final String ADMIN_REGISTER_PATH = "/admin/register";// Public path that admins can use to register.
    public static final String ADMIN_PATH = "/admin/**"; 
    public static final String CLIENT_PATH = "/client/**"; 
    public static final String ORGANIZER_PATH = "/organizer/**"; 
    public static final String EVENT_PATH = "/event/**"; 
    public static final String TICKET_PATH = "/ticket/**"; 
    public static final String IMAGES_PATH ="/images/event-photos/**";


}

