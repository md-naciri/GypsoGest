package com.filrouge.gypsogest.service.implementation;
import com.filrouge.gypsogest.domain.Client;
import com.filrouge.gypsogest.domain.Transaction;
import com.filrouge.gypsogest.exception.CustomException;
import com.filrouge.gypsogest.repository.TransactionRepo;
import com.filrouge.gypsogest.service.ClientService;
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
    private final ClientService clientService;
    @Override
    @Transactional
    public Transaction saveTransaction(Transaction transaction) {
        // Check if a Transaction entity with the same paymentCode already exists
        Optional<Transaction> existingTransaction = transactionRepository.findByPaymentCode(transaction.getPaymentCode());

        if (existingTransaction.isPresent()) {
            throw new CustomException("Transaction with paymentCode " + transaction.getPaymentCode() + " already exists.");
        }
        Client client = clientService.findClientById(transaction.getClient().getId())
                .orElseThrow(() -> new CustomException("Client not found with id: " + transaction.getClient().getId()));
        transaction.setClient(client);

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
        Transaction existingTransaction = transactionRepository.findById(id)
                .orElseThrow(() -> new CustomException("Transaction not found with id: " + id));
        // Retrieve the client by its ID
        Client client = clientService.findClientById(updatedTransaction.getClient().getId())
                .orElseThrow(() -> new CustomException("Client not found with id: " + updatedTransaction.getClient().getId()));

        existingTransaction = Transaction.builder()
                .id(existingTransaction.getId())
                .date(updatedTransaction.getDate())
                .amount(updatedTransaction.getAmount())
                .paymentType(updatedTransaction.getPaymentType())
                .paymentCode(updatedTransaction.getPaymentCode())
                .client(client)
                .build();
        return transactionRepository.save(existingTransaction);
    }

    @Override
    @Transactional
    public void deleteTransaction(Long id) {
        transactionRepository.findById(id)
                .ifPresentOrElse(
                        transactionRepository::delete,
                        () -> { throw new CustomException("Transaction not found with id: " + id); }
                );
    }
}
