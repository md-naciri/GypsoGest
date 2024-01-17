package com.filrouge.gypsogest.service;

import com.filrouge.gypsogest.domain.Sale;
import com.filrouge.gypsogest.domain.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TransactionService {
    Transaction saveTransaction(Transaction transaction);
    Optional<Transaction> findTransactionById(Long id);
    Set<Transaction> findTransactionsByClientId(Long id);
    List<Transaction> findAllTransactions();
    Transaction updateTransaction(Long id, Transaction transaction);
    void deleteTransaction(Long id);
}
