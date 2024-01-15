package com.filrouge.gypsogest.web.vm;

import com.filrouge.gypsogest.domain.Client;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;

public record ClientRequestVM(
        @NotBlank(message = "First name cannot be blank")
        String firstName,

        @NotBlank(message = "Last name cannot be blank")
        String lastName,

        @NotBlank(message = "Email cannot be blank")
        String email,

        @NotBlank(message = "CIN cannot be blank")
        String cin
) {
    public Client toClient(){
        return Client.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .cin(cin)
                //.sales(new HashSet<>())
                //.transactions(new HashSet<>())
                .build();
    }
}
