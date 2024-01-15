package com.filrouge.gypsogest.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.filrouge.gypsogest.domain.enumeration.PaymentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private Double amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @NotNull
    private String paymentCode;


    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = { "sales", "transactions" }, allowSetters = true)
    private Client client;
}
