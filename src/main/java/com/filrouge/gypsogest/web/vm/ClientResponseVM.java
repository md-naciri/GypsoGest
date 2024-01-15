package com.filrouge.gypsogest.web.vm;
import com.filrouge.gypsogest.domain.Client;
import java.util.Set;

public record ClientResponseVM(
        Long id,
        String firstName,
        String lastName,
        String email
        //Set<?> sales, // Replace with the appropriate type
        //Set<?> transactions // Replace with the appropriate type
) {
    public static ClientResponseVM fromClient(Client client){
        return new ClientResponseVM(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail()
                //client.getSales(), // Ensure this is converted to the appropriate VM type if needed
                //client.getTransactions() // Ensure this is converted to the appropriate VM type if needed
        );
    }
}
