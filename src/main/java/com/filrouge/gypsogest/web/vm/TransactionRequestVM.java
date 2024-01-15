package com.filrouge.gypsogest.web.vm;
import com.filrouge.gypsogest.domain.Client;
import com.filrouge.gypsogest.domain.Transaction;
import com.filrouge.gypsogest.domain.enumeration.PaymentType;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record TransactionRequestVM(
        @NotNull LocalDateTime date,
        @NotNull Double amount,
        @NotNull PaymentType paymentType,
        @NotNull String paymentCode,
        @NotNull Long clientId
) {
    public Transaction toTransaction() {
        return Transaction.builder()
                .date(date)
                .amount(amount)
                .paymentType(paymentType)
                .paymentCode(paymentCode)
                .client(Client.builder().id(clientId).build())
                .build();
    }
}
