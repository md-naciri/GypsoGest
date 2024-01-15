package com.filrouge.gypsogest.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String cin;

    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    @JsonIgnoreProperties(value = { "items", "client" }, allowSetters = true)
    private Set<Sale> sales = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    @JsonIgnoreProperties(value = { "client" }, allowSetters = true)
    private Set<Transaction> transactions = new HashSet<>();
}
