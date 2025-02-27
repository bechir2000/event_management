package com.project.event_management.web;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.event_management.config.FileUploadUtil;
import com.project.event_management.entities.Event;
import com.project.event_management.service.EventService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/event")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

    EventService eventService;
    
    @GetMapping("/{id}")
        public ResponseEntity<Event> getEvent(@PathVariable Long id){
            return new ResponseEntity<>(eventService.getEvent(id), HttpStatus.OK);
        }

    @PostMapping("/organizer/{organizerId}")
    public ResponseEntity<Event> saveEvent(@Valid @ModelAttribute Event event, @PathVariable Long organizerId,  @RequestParam("image") MultipartFile multipartFile) throws IOException{
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        event.setPhoto(fileName);
    
        String uploadDir = "public/images/event-photos/";
    
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        
        return new ResponseEntity<Event>(eventService.saveEvent(event, organizerId), HttpStatus.CREATED);

    }


     @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@Valid @RequestBody Event event, @PathVariable Long id) {
        return new ResponseEntity<>(eventService.updateEvent(id, event.getName(), event.getDate(), event.getTime(), event.getLocation(), event.getDescription(), event.getMaxNumOfAttendees()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Event>> getEvents() {
        return new ResponseEntity<>(eventService.getEvents(), HttpStatus.OK);
    }

    @GetMapping("/organizer/{organizerId}")
    public ResponseEntity<List<Event>> getOrganizerEvents(@PathVariable Long organizerId) {
        return new ResponseEntity<>(eventService.getOrganizerEvents(organizerId), HttpStatus.OK);
    }

}
