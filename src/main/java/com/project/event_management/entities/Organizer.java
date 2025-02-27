package com.project.event_management.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("ORGANIZER")
public class Organizer extends User {

    public Organizer(){
        this.role=Role.ORGANIZER;
    }
    
    @JsonIgnore
    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL)
    private List<Event> events; 

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Ticket> tickets; 
}
