package com.filrouge.gypsogest.web.vm;

import com.filrouge.gypsogest.domain.Client;
import com.filrouge.gypsogest.domain.Item;
import com.filrouge.gypsogest.domain.Sale;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record SaleRequestVM(
        @NotNull(message = "Date cannot be null")
        LocalDateTime date,
        @NotNull(message = "Client cannot be null")
        Long clientId,
        @NotEmpty(message = "Items cannot be empty")
        @Valid
        Set<ItemRequestVM> items
) {
    public Sale toSale() {
        Sale sale = Sale.builder()
                .date(date)
                .client(Client.builder().id(clientId).build())
                .build();

        Set<Item> saleItems = items.stream()
                .map(itemRequestVM -> itemRequestVM.toItem(sale))
                .collect(Collectors.toSet());

        sale.setItems(saleItems);
        return sale;
    }
}
