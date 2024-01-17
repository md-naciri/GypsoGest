package com.filrouge.gypsogest.web.vm;

import com.filrouge.gypsogest.domain.Client;
import com.filrouge.gypsogest.domain.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public record DebitResponseVM(
        ClientResponseVM client,
        List<TransactionResponseVM> transactions,
        Double debit
) {
    public static DebitResponseVM fromClientAndTransactions(Client client, List<Transaction> transactions, Double debit) {
        List<TransactionResponseVM> transactionResponses = transactions.stream()
                .map(TransactionResponseVM::fromTransaction)
                .collect(Collectors.toList());

        return new DebitResponseVM(
                ClientResponseVM.fromClient(client),
                transactionResponses,
                debit
        );
    }
}
