package com.filrouge.gypsogest.web.vm;
import com.filrouge.gypsogest.domain.Returned;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ReturnedRequestVM(
        @NotNull LocalDateTime date,
        @NotNull String paymentCode
) {
    public Returned toReturned() {
        return Returned.builder()
                .date(date)
                .paymentCode(paymentCode)
                .build();
    }
}
