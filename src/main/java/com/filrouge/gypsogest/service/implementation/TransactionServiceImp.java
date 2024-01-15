package com.filrouge.gypsogest.service.implementation;

import com.filrouge.gypsogest.domain.Transaction;
import com.filrouge.gypsogest.repository.TransactionRepo;
import com.filrouge.gypsogest.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImp implements TransactionService {
    private final TransactionRepo transactionRepository;
    @Override
    @Transactional
    public Transaction saveTransaction(Transaction transaction) {
        // Check if a Transaction entity with the same paymentCode already exists
        Optional<Transaction> existingTransaction = transactionRepository.findByPaymentCode(transaction.getPaymentCode());

        if (existingTransaction.isPresent()) {
            // Entity with the same paymentCode already exists, handle the situation accordingly
            // You can throw an exception, return null, or handle it as needed.
            throw new RuntimeException("Transaction with paymentCode " + transaction.getPaymentCode() + " already exists.");
        }

        return transactionRepository.save(transaction);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Transaction> findTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    @Transactional
    public Transaction updateTransaction(Long id, Transaction updatedTransaction) {
        return transactionRepository.findById(id)
                .map(existingTransaction -> {
                    existingTransaction.setDate(updatedTransaction.getDate());
                    existingTransaction.setAmount(updatedTransaction.getAmount());
                    existingTransaction.setPaymentType(updatedTransaction.getPaymentType());
                    existingTransaction.setPaymentCode(updatedTransaction.getPaymentCode());
                    existingTransaction.setClient(updatedTransaction.getClient());
                    return transactionRepository.save(existingTransaction);
                })
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
    }

    @Override
    @Transactional
    public void deleteTransaction(Long id) {
        transactionRepository.findById(id)
                .ifPresentOrElse(
                        transactionRepository::delete,
                        () -> { throw new RuntimeException("Transaction not found with id: " + id); }
                );
    }
}
