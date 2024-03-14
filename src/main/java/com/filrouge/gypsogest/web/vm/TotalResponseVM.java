package com.filrouge.gypsogest.web.vm;

import com.filrouge.gypsogest.domain.Client;

public record TotalResponseVM(
        ClientResponseVM client,
        Double credit,
        Double debit,
        Double total
) {
    public static TotalResponseVM fromClient(Client client, Double total, Double credit, Double debit) {
        return new TotalResponseVM(
                ClientResponseVM.fromClient(client),
                credit,
                debit,
                total
        );
    }
}
