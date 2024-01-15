package com.filrouge.gypsogest.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.filrouge.gypsogest.domain.enumeration.GypseType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Double quantity;

    @NotNull
    private Double unitPrice;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GypseType gypseType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "items", "client" }, allowSetters = true)
    private Sale sale;
}