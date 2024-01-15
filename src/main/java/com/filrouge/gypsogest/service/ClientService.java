package com.filrouge.gypsogest.service;

import com.filrouge.gypsogest.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Client saveClient(Client client);
    Optional<Client> findClientById(Long id);
    List<Client> findAllClients();
    Client updateClient(Long id, Client updatedClient);
    void deleteClient(Long id);
}
