package com.filrouge.gypsogest.service.implementation;

import com.filrouge.gypsogest.domain.Client;
import com.filrouge.gypsogest.exception.CustomException;
import com.filrouge.gypsogest.repository.ClientRepo;
import com.filrouge.gypsogest.service.ClientService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImp implements ClientService {
    private final ClientRepo clientRepository;
    @Override
    @Transactional
    public Client saveClient(Client client) {
        // Check if client with the same CIN already exists
        Optional<Client> existingClient = clientRepository.findByCin(client.getCin());
        if (existingClient.isPresent()) {
            throw new CustomException("Client with CIN " + client.getCin() + " already exists.");
        }
        // save a client
        return clientRepository.save(client);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> findClientById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional
    public Client updateClient(Long id, Client updatedClient) {
        return clientRepository.findById(id)
                .map(client -> {
                    client.setFirstName(updatedClient.getFirstName());
                    client.setLastName(updatedClient.getLastName());
                    client.setEmail(updatedClient.getEmail());
                    return clientRepository.save(client);
                })
                .orElseThrow(() -> new CustomException("Client not found with id: " + id));
    }

    @Override
    @Transactional
    public void deleteClient(Long id) {
        clientRepository.findById(id)
                .ifPresentOrElse(
                        clientRepository::delete,
                        () -> { throw new CustomException("Client not found with id: " + id); }
                );
    }
}
