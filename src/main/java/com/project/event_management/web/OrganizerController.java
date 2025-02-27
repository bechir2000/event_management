package com.project.event_management.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.event_management.entities.Organizer;
import com.project.event_management.service.OrganizerService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor 
@RestController
@RequestMapping("/organizer")
@CrossOrigin(origins = "http://localhost:3000")
public class OrganizerController {
    OrganizerService organizerService;

    @GetMapping("/{id}")
        public ResponseEntity<Organizer> getOrganizer(@PathVariable Long id){
            return new ResponseEntity<>(organizerService.getOrganizer(id), HttpStatus.OK);
        }

    @PostMapping("/register")
    public ResponseEntity<Organizer> saveOrganizer(@Valid @RequestBody Organizer organizer){
        organizerService.saveOrganizer(organizer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Organizer> updateOrganizer(@Valid @RequestBody Organizer organizer, @PathVariable Long id) {
        return new ResponseEntity<>(organizerService.updateOrganizer(id, organizer.getFirstName(), organizer.getLastName(), organizer.getPhoneNumber(), organizer.getPassword()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteOrganizer(@PathVariable Long id) {
        organizerService.deleteOrganizer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Organizer>> getOrganizers() {
        return new ResponseEntity<>(organizerService.getOrganizers(), HttpStatus.OK);
    }
}
