package com.project.event_management.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="events")
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")    
    private Long id;

    @NotBlank(message="event name cannot be blank!")
    @Size(min=4 , message = "event name is too short")
    @Column(name = "name", nullable = false)
    protected String name;

    @Future(message="date must be in the future")
    @NonNull 
    @Column(name = "date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NonNull
    @Column(name = "time", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime time;

    @NotBlank(message="location cannot be blank!")
    @Column(name = "location", nullable = false)
    private String location;

    @NotBlank(message="description cannot be blank!")
    @Size(min = 10, message = "description is too short!")
    @Column(name = "description", nullable = false)
    private String description;

    @NotBlank(message="category cannot be blank!")
    @Column(name = "category", nullable = false)
    private String category;

    @NonNull
    @Min(0)
    private Integer maxNumOfAttendees;

    @NonNull
    @Min(0)
    private Float price;

    @Column(nullable = true, length = 64)
    private String photo;

    @ManyToOne(optional = false)
    @JsonIgnore //to secure the organizer details!
    @JoinColumn(name="organizer_id", referencedColumnName = "id")
    private Organizer organizer;

    @JsonIgnore
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Ticket> tickets;
    
    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "event_client",
        joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id")
    )
    private Set<Client> attendees;


    @Transient
    public String getPhotosImagePath() {
        if (photo == null || id == null) return null;
         
        return "/user-photos/" + id + "/" + photo;
    }

}
