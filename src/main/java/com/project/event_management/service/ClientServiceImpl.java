package com.project.event_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.event_management.entities.Client;
import com.project.event_management.exceptions.UserNotFoundException;
import com.project.event_management.repository.ClientRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService{

    private ClientRepository clientRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Client getClient(Long id) {
        Optional<Client> client = clientRepository.findById(id); //looking for client in the data base
        if (client.isPresent()){
            return client.get(); //return it if he exists 
        }else {
            throw new UserNotFoundException("client",id);
        }
    }

    /*@Override
    public Client getClientByEmail(String email) {
        Optional<Client> client = clientRepository.findByEmail(email);
        if (client.isPresent()){
            return client.get();
        }else {
            throw new UserNotFoundException("client",00L);
        }
    }*/

    @Override
    public Client saveClient(Client client) {
        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword())); //save with encrypted password
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        Optional<Client> client= clientRepository.findById(id);//look for the client by id in the data base
        if(client.isPresent()){
            clientRepository.deleteById(id); //if the client exists delete it
        }else {
            throw new UserNotFoundException("client",id);
        }
    }

    public Client updateClient(Long id,String fName, String lName,String phoneNumber, String password){
        Optional<Client> oldClient = clientRepository.findById(id); //look for cient in the databse 
        if(oldClient.isPresent()){ //if he exists
            Client client= oldClient.get(); //update
            client.setFirstName(fName);
            client.setLastName(lName);
            client.setPhoneNumber(phoneNumber);
            client.setPassword(bCryptPasswordEncoder.encode(password));
            return clientRepository.save(client); //save the updated client
        }else {
            throw new UserNotFoundException("client", id);
        }
    }

    @Override
    public List<Client> getClients() {
        return (List<Client>)clientRepository.findAll();
    }

    
    
}
