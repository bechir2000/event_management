package com.project.event_management.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
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
@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")    
    protected Long id;

    @NotBlank(message="First name cannot be blank!")
    @Size(min = 2, message = "First name is too short")
    @Column(name = "first_name", nullable = false)
    protected String firstName;

    @NotBlank(message="Last name cannot be blank!")
    @Size(min = 2, message = "Last name is too short")
    @Column(name = "last_name", nullable = false)
    protected String lastName;

    @NotBlank(message="Phone number cannot be blank!")
    @Column(name = "phone_number", nullable = false)
    protected String phoneNumber;

    @NotBlank(message="Email cannot be blank!")
    @Email
    @Column(name = "email", nullable = false, unique = true)
    protected String email;

    @NotBlank(message="Password cannot be blank!")
    @Size(min = 8, message = "Password is too short")
    @Column(name = "password", nullable = false)
    protected String password;

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    protected Role role;
}
