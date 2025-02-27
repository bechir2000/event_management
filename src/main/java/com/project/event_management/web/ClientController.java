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

import com.project.event_management.entities.Client;
import com.project.event_management.service.ClientService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor 
@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "http://localhost:3000")
public class ClientController {
    ClientService clientService;

    @GetMapping("/{id}")
        public ResponseEntity<Client> getClient(@PathVariable Long id){
            return new ResponseEntity<>(clientService.getClient(id), HttpStatus.OK);
        }

    @PostMapping("/register")
    public ResponseEntity<Client> saveClient(@Valid @RequestBody Client client){
        clientService.saveClient(client);
        return new ResponseEntity<Client>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@Valid @RequestBody Client client, @PathVariable Long id) {
        return new ResponseEntity<>(clientService.updateClient(id, client.getFirstName(), client.getLastName(), client.getPhoneNumber(), client.getPassword()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Client>> getClients() {
        return new ResponseEntity<>(clientService.getClients(), HttpStatus.OK);
    }
}
