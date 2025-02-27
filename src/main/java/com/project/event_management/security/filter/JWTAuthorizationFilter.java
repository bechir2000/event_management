package com.project.event_management.security.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.project.event_management.security.SecurityConstants;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthorizationFilter extends OncePerRequestFilter{
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization"); //get the header from request

        if (header == null || !header.startsWith(SecurityConstants.BEARER)) { //if there is no header or no bearer token
            filterChain.doFilter(request, response); // try to sign in or sign up
            return;
        }

        String token = header.replace(SecurityConstants.BEARER, ""); //get the token from the header
        

        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET_KEY))
                .build()
                .verify(token); //verify the JWT token by using the secret key

        String user = decodedJWT.getSubject(); //get username from the token payload
        List<String> roles = decodedJWT.getClaim("roles").asList(String.class); // Retrieve roles from the token payload

        Collection<GrantedAuthority> authorities = roles.stream() //create a collection of GrantedAuthority from the user role
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities); // create an athentication with user email and his role
        SecurityContextHolder.getContext().setAuthentication(authentication); 
        filterChain.doFilter(request, response); //proceed the request 
    }
}
