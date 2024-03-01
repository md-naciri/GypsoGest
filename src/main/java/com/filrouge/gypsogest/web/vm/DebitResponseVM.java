package com.filrouge.gypsogest.web.vm;

import com.filrouge.gypsogest.domain.Client;
import com.filrouge.gypsogest.domain.Returned;
import com.filrouge.gypsogest.domain.Transaction;
import com.filrouge.gypsogest.domain.enumeration.PaymentType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DebitResponseVM(
        ClientResponseVM client,
        List<TransactionWithoutClientResponseVM> transactions,
        List<Returned> listOfReturned,
        Double debit
) {
    public static DebitResponseVM fromClientAndTransactions(Client client, List<Transaction> transactions, List<Returned> listOfReturned, Double debit) {
        List<TransactionWithoutClientResponseVM> transactionResponses = transactions.stream()
                .map(TransactionWithoutClientResponseVM::fromTransaction)
                .collect(Collectors.toList());

        return new DebitResponseVM(
                ClientResponseVM.fromClient(client),
                transactionResponses,
                listOfReturned,
                debit
        );
    }
}

record TransactionWithoutClientResponseVM(
        Long id,
        LocalDateTime date,
        PaymentType paymentType,
        String paymentCode,
        Double amount

) {
    public static TransactionWithoutClientResponseVM fromTransaction(Transaction transaction){
        return new TransactionWithoutClientResponseVM(
                transaction.getId(),
                transaction.getDate(),
                transaction.getPaymentType(),
                transaction.getPaymentCode(),
                transaction.getAmount()
        );
    }
}
