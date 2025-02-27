package com.project.event_management.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.event_management.entities.Role;
import com.project.event_management.entities.User;
import com.project.event_management.entities.UserDetails;
import com.project.event_management.security.SecurityConstants;
import com.project.event_management.security.manager.CustomAuthenticationManager;
import com.project.event_management.service.UserService;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
    private CustomAuthenticationManager authenticationManager;
    private UserService userService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
       try {
        UserDetails userDetails = new ObjectMapper().readValue(request.getInputStream(), UserDetails.class); //get the user details from the request
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getEmail(), userDetails.getPassword()); //create an authentication with the user details
        return authenticationManager.authenticate(authentication); //try to autheticate the user

        } catch(IOException e){
            throw new RuntimeException();
       }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(failed.getMessage());
        response.getWriter().flush();
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String username = authResult.getName();
        User user = userService.getUserByEmail(username);

        Role userRole = user.getRole(); // Retrieve the user's role

        List<String> authorities = new ArrayList<>();
        authorities.add(userRole.getAuthority()); // Assign authorities based on the user's role
        
        String token = JWT.create()
        .withSubject(username) //adding the email to the token payload 
        .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION)) //adding the expiration date to the payload
        .withClaim("roles", authorities) // Include roles in the token payload 
        .sign(Algorithm.HMAC512(SecurityConstants.SECRET_KEY)); // adding secret key to verify later 
    
        // Create a response JSON object
        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("token", token);
        responseObj.put("role", userRole);
        responseObj.put("userId", user.getId());
        // Add more data to the responseObj if needed

        // Set the content type to application/json
        response.setContentType("application/json");
        // Write the response JSON object to the response body
        response.getWriter().write(new ObjectMapper().writeValueAsString(responseObj));
        // Set the HTTP status code to 200 (OK)
        response.setStatus(HttpServletResponse.SC_OK);
    }
    
        


}

