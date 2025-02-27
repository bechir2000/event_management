package com.project.event_management.service;

import java.util.List;

import com.project.event_management.entities.Client;

public interface ClientService {
    Client getClient(Long id);
   // Client getClientByEmail(String email);
    Client saveClient(Client client);
    void deleteClient(Long id);
    Client updateClient(Long id,String fName, String lName,String phoneNumber, String password);
    List<Client> getClients();
}
