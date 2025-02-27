package com.project.event_management.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    public Admin(){
        this.role=Role.ADMIN;
    }
    
}
