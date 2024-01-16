package com.filrouge.gypsogest.web.vm;

import com.filrouge.gypsogest.domain.Client;
import com.filrouge.gypsogest.domain.Sale;

import java.util.List;
import java.util.stream.Collectors;

public record CreditResponseVM(
        ClientResponseVM client,
        List<SaleResponseVM> sales,
        Double credit
) {
    public static CreditResponseVM fromClientAndSales(Client client, List<Sale> sales, Double credit) {
        List<SaleResponseVM> saleResponses = sales.stream()
                .map(sale -> SaleResponseVM.fromSale(sale, false))
                .collect(Collectors.toList());

        return new CreditResponseVM(
                ClientResponseVM.fromClient(client),
                saleResponses,
                credit
        );
    }
}

