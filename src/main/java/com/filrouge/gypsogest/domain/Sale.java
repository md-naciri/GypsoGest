package com.filrouge.gypsogest.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "sales", "transactions" }, allowSetters = true)
    private Client client;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sale", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties(value = { "sale" }, allowSetters = true)
    private Set<Item> items = new HashSet<>();
}
