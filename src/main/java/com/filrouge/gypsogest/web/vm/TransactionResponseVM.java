package com.filrouge.gypsogest.web.vm;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.filrouge.gypsogest.domain.Client;
import com.filrouge.gypsogest.domain.Transaction;
import com.filrouge.gypsogest.domain.enumeration.PaymentType;
import java.time.LocalDateTime;

public record TransactionResponseVM(
        Long id,
        LocalDateTime date,
        Double amount,
        PaymentType paymentType,
        String paymentCode,
        @JsonIgnoreProperties(value = { "sales", "transactions" }, allowSetters = true)
        Client client
) {
    public static TransactionResponseVM fromTransaction(Transaction transaction){
        return new TransactionResponseVM(
                transaction.getId(),
                transaction.getDate(),
                transaction.getAmount(),
                transaction.getPaymentType(),
                transaction.getPaymentCode(),
                transaction.getClient()
        );
    }
}
