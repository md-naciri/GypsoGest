package com.filrouge.gypsogest.service;

import java.util.List;

public interface AccountingService {
    // Calculate credit for a single client
    double calculateCreditForClient(Long clientId);

    // Calculate debit for a single client
    double calculateDebitForClient(Long clientId);

    // Calculate total (debit and credit) for a single client
    double calculateTotalForClient(Long clientId);

    // Calculate combined credit for multiple clients
    double calculateCombinedCreditForClients(List<Long> clientIds);

    // Calculate combined debit for multiple clients
    double calculateCombinedDebitForClients(List<Long> clientIds);

    // Calculate combined total for multiple clients
    double calculateCombinedTotalForClients(List<Long> clientIds);

    // Calculate total credit for all clients
    double calculateTotalCreditForAllClients();

    // Calculate total debit for all clients
    double calculateTotalDebitForAllClients();

    // Calculate overall total (credit and debit) for all clients
    double calculateOverallTotalForAllClients();
}
