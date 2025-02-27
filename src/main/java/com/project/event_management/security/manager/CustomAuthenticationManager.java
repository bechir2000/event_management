package com.project.event_management.security.manager; 

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.project.event_management.entities.Role;
import com.project.event_management.entities.User;
import com.project.event_management.service.UserServiceImpl;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {    

    
    private UserServiceImpl userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user =userService.getUserByEmail(authentication.getName()); //look for user in the data base

        if (!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
            throw new BadCredentialsException("incorrect password !"); //check the password
        }

        
        Role userRole = user.getRole(); // Retrieve the user's role

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userRole.name())); // Assign authorities based on the user's role

        if (userRole == Role.ADMIN) {
            authorities.add(new SimpleGrantedAuthority(Role.ALL_PERMISSIONS_AUTHORITY.name())); //if the user is an admin, give him all authorities
        }

        return new UsernamePasswordAuthenticationToken(authentication.getName(), user.getPassword(), authorities);
    }
}
