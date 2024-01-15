package com.filrouge.gypsogest.web.vm;
import com.filrouge.gypsogest.domain.Returned;
import java.time.LocalDateTime;

public record ReturnedResponseVM(
        Long id,
        LocalDateTime date,
        String paymentCode
) {
    public static ReturnedResponseVM fromReturned(Returned returned){
        return new ReturnedResponseVM(
                returned.getId(),
                returned.getDate(),
                returned.getPaymentCode()
        );
    }
}
