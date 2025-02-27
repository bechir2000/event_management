package com.project.event_management.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.project.event_management.entities.Role;
import com.project.event_management.security.filter.AuthenticationFilter;
import com.project.event_management.security.filter.ExceptionHandlerFilter;
import com.project.event_management.security.filter.JWTAuthorizationFilter;
import com.project.event_management.security.manager.CustomAuthenticationManager;
import com.project.event_management.service.UserServiceImpl;

import lombok.AllArgsConstructor;

import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class SecurityConfig {

    private final CustomAuthenticationManager authenticationManager;
    private UserServiceImpl userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter= new AuthenticationFilter(authenticationManager, userService);
        authenticationFilter.setFilterProcessesUrl("/authenticate");
        http        
            .csrf().disable()
            .cors()
            .and()
            .authorizeRequests()
            .requestMatchers(new AntPathRequestMatcher("/h2/**")).permitAll() // allows us to access the h2 console without the need to authenticate.           
            .requestMatchers(HttpMethod.POST, SecurityConstants.CLIENT_REGISTER_PATH).permitAll() //permit signing up for the clients
            .requestMatchers(HttpMethod.POST, SecurityConstants.ADMIN_REGISTER_PATH).permitAll() //permit signing up for the admin
            .requestMatchers(HttpMethod.POST, SecurityConstants.ORGANIZER_REGISTER_PATH).permitAll() //permit signing up for the organizers
            .requestMatchers(HttpMethod.GET,SecurityConstants.EVENT_PATH).permitAll()
            .requestMatchers(HttpMethod.GET,SecurityConstants.IMAGES_PATH).permitAll()
            .requestMatchers(HttpMethod.POST, SecurityConstants.EVENT_PATH).hasRole(Role.ORGANIZER.name())
            .requestMatchers(HttpMethod.DELETE, SecurityConstants.EVENT_PATH).hasAnyRole(Role.ORGANIZER.name(), Role.ADMIN.name())
            .requestMatchers(HttpMethod.PUT, SecurityConstants.EVENT_PATH).hasRole(Role.ORGANIZER.name())
            .requestMatchers(SecurityConstants.ADMIN_PATH).hasRole(Role.ADMIN.name()) // Require ADMIN role
            .requestMatchers(SecurityConstants.TICKET_PATH).hasAnyRole(Role.ADMIN.name(),Role.ORGANIZER.name(),Role.CLIENT.name()) 
            .requestMatchers(HttpMethod.GET, SecurityConstants.CLIENT_PATH).hasAnyRole(Role.CLIENT.name(), Role.ADMIN.name())
            .requestMatchers(HttpMethod.DELETE, SecurityConstants.CLIENT_PATH).hasAnyRole(Role.CLIENT.name(), Role.ADMIN.name())
            .requestMatchers(HttpMethod.PUT, SecurityConstants.CLIENT_PATH).hasRole(Role.CLIENT.name())
            .requestMatchers(HttpMethod.GET, SecurityConstants.ORGANIZER_PATH).hasAnyRole(Role.ORGANIZER.name(), Role.ADMIN.name())
            .requestMatchers(HttpMethod.DELETE, SecurityConstants.ORGANIZER_PATH).hasAnyRole(Role.ORGANIZER.name(), Role.ADMIN.name())
            .requestMatchers(HttpMethod.PUT, SecurityConstants.ORGANIZER_PATH).hasRole(Role.ORGANIZER.name())
            .anyRequest().authenticated() //all other request require authentication
            .and()
            .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class) //passing by the exception filter before trying authentication
            .addFilter(authenticationFilter) //trying authentication
            .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class) // verifying the token
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //making the session stateless (expire by time )

        http.headers().frameOptions().disable(); // the h2 console runs on a "frame". By default, Spring Security prevents rendering within an iframe. This line disables its prevention.
        return http.build();
    }

    
}