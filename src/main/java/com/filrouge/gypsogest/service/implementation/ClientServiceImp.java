package com.filrouge.gypsogest.service.implementation;

import com.filrouge.gypsogest.domain.Client;
import com.filrouge.gypsogest.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImp implements ClientService {
    @Override
    public Client saveClient(Client client) {
        return null;
    }

    @Override
    public Optional<Client> findClientById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Client> findAllClients() {
        return null;
    }

    @Override
    public Client updateClient(Long id, Client updatedClient) {
        return null;
    }

    @Override
    public void deleteClient(Long id) {

    }
}
