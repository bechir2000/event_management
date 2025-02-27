package com.project.event_management.entities;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("CLIENT")
public class Client extends User{
    
    public Client(){
        this.role=Role.CLIENT;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Ticket> tickets; 

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "event_client",
        joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id")
    )
    private Set<Event> events;
}

