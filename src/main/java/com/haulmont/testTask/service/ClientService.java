package com.haulmont.testTask.service;

import com.haulmont.testTask.entity.Client;
import com.haulmont.testTask.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    private final ClientRepository  clientRepository;
    @Autowired
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public void addClient(Client client){
        Optional<Client> tmpClient = clientRepository.findByFullNameEqualsAndPhoneNumberEqualsAndEmailEqualsAndPassportNumberEquals(
                client.getFullName(), client.getPhoneNumber(), client.getEmail(), client.getPassportNumber());
        tmpClient.ifPresent(clientRepository::delete);
        clientRepository.save(client);
    }

    public Client getClient(UUID id){
       return clientRepository.findById(id).get();
    }

}
