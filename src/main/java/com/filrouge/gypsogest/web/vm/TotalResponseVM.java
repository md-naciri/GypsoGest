package com.filrouge.gypsogest.web.vm;

import com.filrouge.gypsogest.domain.Client;

public record TotalResponseVM(
        ClientResponseVM client,
        Double total
) {
    public static TotalResponseVM fromClient(Client client, Double total) {
        return new TotalResponseVM(
                ClientResponseVM.fromClient(client),
                total
        );
    }
}
