package com.filrouge.gypsogest.service.implementation;

import com.filrouge.gypsogest.domain.Client;
import com.filrouge.gypsogest.domain.Returned;
import com.filrouge.gypsogest.domain.Transaction;
import com.filrouge.gypsogest.exception.CustomException;
import com.filrouge.gypsogest.repository.ReturnedRepo;
import com.filrouge.gypsogest.service.ClientService;
import com.filrouge.gypsogest.service.ReturnedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ReturnedServiceImp implements ReturnedService {
    private final ReturnedRepo returnedRepository;
    private final ClientService clientService;
    @Override
    @Transactional
    public Returned saveReturned(Returned returned) {
        // Check if a Returned entity with the same paymentCode already
        //Optional<Returned> existingReturned = returnedRepository.findByPaymentCode(returned.getPaymentCode());
        clientService.findClientById(returned.getClientId())
                .orElseThrow(() -> new CustomException("Client not found with id: " + returned.getClientId()));
        Optional<Returned> existingReturned = returnedRepository.findByPaymentCodeAndClientId(returned.getPaymentCode(), returned.getClientId());

        if (existingReturned.isPresent()) {
            // Entity with the same paymentCode already exists, handle the situation accordingly
            // You can throw an exception, return null, or handle it as needed.
            throw new CustomException("Returned with paymentCode " + returned.getPaymentCode() + " and client id " + returned.getClientId() + " already exists.");
        }

        return returnedRepository.save(returned);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Returned> findReturnedById(Long id) {
        return returnedRepository.findById(id);
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<Returned> findReturnedByPaymentCodeAndClientId(String paymentCode, Long clientId) {
        return returnedRepository.findByPaymentCodeAndClientId(paymentCode, clientId);
    }

    @Override
    public Set<Returned> findReturnedsByClientId(Long id) {
        // Ensure that the client exists
        clientService.findClientById(id)
                .orElseThrow(() -> new CustomException("Client not found with id: " + id));
        return returnedRepository.findByClientId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Returned> findAllReturneds() {
        return returnedRepository.findAll();
    }


    @Override
    @Transactional
    public Returned updateReturned(Long id, Returned updatedReturned) {
        return returnedRepository.findById(id)
                .map(existingReturned -> {
                    existingReturned.setDate(updatedReturned.getDate());
                    existingReturned.setPaymentCode(updatedReturned.getPaymentCode());
                    existingReturned.setClientId(updatedReturned.getClientId());
                    return returnedRepository.save(existingReturned);
                })
                .orElseThrow(() -> new CustomException("Returned not found with id: " + id));
    }

    @Override
    @Transactional
    public void deleteReturned(Long id) {
        returnedRepository.findById(id)
                .ifPresentOrElse(
                        returnedRepository::delete,
                        () -> { throw new CustomException("Returned not found with id: " + id); }
                );
    }
}
