package com.project.event_management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets", uniqueConstraints={
    @UniqueConstraint(columnNames = {"event_id", "user_id"})
}) 
public class Ticket {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")  
    private Long id;

    @NonNull
    @Min(0)
    private Float price;

    @ManyToOne(optional = false)
    @JoinColumn(name="event_id", referencedColumnName = "id")
    private Event event;

    @ManyToOne(optional = false)
    @JsonIgnore //To secure the client details!
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

}
