package com.filrouge.gypsogest.web.vm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.filrouge.gypsogest.domain.Client;
import com.filrouge.gypsogest.domain.Sale;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record CreditResponseVM(
        ClientResponseVM client,
        List<SaleWithoutClientResponseVM> sales,
        Double credit
) {
    public static CreditResponseVM fromClientAndSales(Client client, List<Sale> sales, Double credit) {
        List<SaleWithoutClientResponseVM> saleResponses = sales.stream()
                .map(SaleWithoutClientResponseVM::fromSale)
                .collect(Collectors.toList());

        return new CreditResponseVM(
                ClientResponseVM.fromClient(client),
                saleResponses,
                credit
        );
    }
}
record SaleWithoutClientResponseVM(
        Long id,
        LocalDateTime date,
        @JsonIgnoreProperties(value = { "sale" }, allowSetters = true)
        Set<ItemResponseVM> items
) {
    public static SaleWithoutClientResponseVM fromSale(Sale sale){
        return new SaleWithoutClientResponseVM(
                sale.getId(),
                sale.getDate(),
                sale.getItems().stream().map(ItemResponseVM::fromItem).collect(Collectors.toSet())
        );
    }
}