package com.project.event_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.event_management.entities.Organizer;
import com.project.event_management.exceptions.UserNotFoundException;
import com.project.event_management.repository.OrganizerRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OrganizerServiceImpl implements OrganizerService {

    private OrganizerRepository organizerRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Organizer getOrganizer(Long id) {
        Optional<Organizer> organizer= organizerRepository.findById(id); //look for organizer in the databse 
        if(organizer.isPresent()){
            return organizer.get(); //return it if he exists
        } else {
            throw new UserNotFoundException("organizer",id);
        }
    }

    @Override
    public Organizer saveOrganizer(Organizer organizer) {
        organizer.setPassword(bCryptPasswordEncoder.encode(organizer.getPassword()));
        return organizerRepository.save(organizer); //save with encrypted password
    }

    @Override
    public void deleteOrganizer(Long id) {
        Optional<Organizer> organizer= organizerRepository.findById(id); // look for the organizer in the databse
        if(organizer.isPresent()){
            organizerRepository.deleteById(id); //delete it if he exists   
        } else {
            throw new UserNotFoundException("organizer",id);
        }
    }

    public Organizer updateOrganizer(Long id,String fName, String lName,String phoneNumber, String password){
        Optional<Organizer> oldOrganizer = organizerRepository.findById(id); //look for the organizer in the databse 
        if(oldOrganizer.isPresent()){ //if he exists
            Organizer organizer= oldOrganizer.get(); //update 
            organizer.setFirstName(fName);
            organizer.setLastName(lName);
            organizer.setPhoneNumber(phoneNumber);
            organizer.setPassword(bCryptPasswordEncoder.encode(password));
            return organizerRepository.save(organizer); //save the updated organizer
        }else {
            throw new UserNotFoundException("organizer", id);
        }
    }

    @Override
    public List<Organizer> getOrganizers() {
        return (List<Organizer>)organizerRepository.findAll();
    }

    
    
}
