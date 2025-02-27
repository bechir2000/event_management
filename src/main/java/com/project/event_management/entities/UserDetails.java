package com.project.event_management.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {

    @NotBlank(message="Email cannot be blank!")
    @Email
    protected String email;

    @NotBlank(message="Password cannot be blank!")
    @Size(min = 8, message = "Password is too short")
    protected String password;
    
    
}
