package com.filrouge.gypsogest.web.resource;

import com.filrouge.gypsogest.domain.Transaction;
import com.filrouge.gypsogest.exception.ResponseHandler;
import com.filrouge.gypsogest.service.TransactionService;
import com.filrouge.gypsogest.web.vm.TransactionRequestVM;
import com.filrouge.gypsogest.web.vm.TransactionResponseVM;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionResource {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> createTransaction(@Valid @RequestBody TransactionRequestVM transactionRequest) {
        Transaction transaction = transactionRequest.toTransaction();
        transaction = transactionService.saveTransaction(transaction);
        TransactionResponseVM transactionResponse = TransactionResponseVM.fromTransaction(transaction);
        return ResponseHandler.created(transactionResponse, "Transaction created successfully.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable Long id) {
        return transactionService.findTransactionById(id)
                .map(transaction -> ResponseHandler.ok(TransactionResponseVM.fromTransaction(transaction), "Transaction found."))
                .orElse(ResponseHandler.notFound("Transaction not found."));
    }

    @GetMapping
    public ResponseEntity<?> getAllTransactions() {
        List<Transaction> transactions = transactionService.findAllTransactions();
        List<TransactionResponseVM> transactionResponses = transactions.stream()
                .map(TransactionResponseVM::fromTransaction)
                .collect(Collectors.toList());
        return ResponseHandler.ok(transactionResponses, "All transactions retrieved successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable Long id, @Valid @RequestBody TransactionRequestVM transactionRequest) {
        Transaction updatedTransaction = transactionRequest.toTransaction();
        Transaction transaction = transactionService.updateTransaction(id, updatedTransaction);
        TransactionResponseVM transactionResponse = TransactionResponseVM.fromTransaction(transaction);
        return ResponseHandler.ok(transactionResponse, "Transaction updated successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseHandler.ok(null, "Transaction deleted successfully.");
    }
}
