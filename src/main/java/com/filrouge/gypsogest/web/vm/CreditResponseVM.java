package com.filrouge.gypsogest.web.vm;

import com.filrouge.gypsogest.domain.Sale;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record CreditResponseVM(
        Long id,
        LocalDateTime date,
        ClientResponseVM client,
        Set<ItemResponseVM> items,
        Double credit
) {
    public static CreditResponseVM fromSale(Sale sale) {
        Set<ItemResponseVM> itemVMs = sale.getItems().stream()
                .map(ItemResponseVM::fromItem)
                .collect(Collectors.toSet());
        double credit = itemVMs.stream()
                .mapToDouble(item -> item.quantity() * item.unitPrice())
                .sum();

        return new CreditResponseVM(
                sale.getId(),
                sale.getDate(),
                ClientResponseVM.fromClient(sale.getClient()),
                itemVMs,
                credit
        );
    }
}
