package com.filrouge.gypsogest.web.vm;
import com.filrouge.gypsogest.domain.Client;
import java.util.Set;

public record ClientResponseVM(
        Long id,
        String FirstName,
        String LastName,
        String Email,
        String CIN
) {
    public static ClientResponseVM fromClient(Client client){
        return new ClientResponseVM(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getCin()
        );
    }
}
