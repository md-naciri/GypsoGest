package com.filrouge.gypsogest.web.vm;
import com.filrouge.gypsogest.domain.Returned;
import java.time.LocalDateTime;

public record ReturnedResponseVM(
        Long id,
        LocalDateTime date,
        Long clientId,
        String paymentCode
) {
    public static ReturnedResponseVM fromReturned(Returned returned){
        return new ReturnedResponseVM(
                returned.getId(),
                returned.getDate(),
                returned.getClientId(),
                returned.getPaymentCode()
        );
    }
}
