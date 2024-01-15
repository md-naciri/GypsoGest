package com.filrouge.gypsogest.service;

import com.filrouge.gypsogest.domain.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Transaction saveTransaction(Transaction transaction);
    Optional<Transaction> findTransactionById(Long id);
    List<Transaction> findAllTransactions();
    Transaction updateTransaction(Long id, Transaction transaction);
    void deleteTransaction(Long id);
}
