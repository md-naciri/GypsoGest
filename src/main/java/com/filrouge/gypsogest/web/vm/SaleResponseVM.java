package com.filrouge.gypsogest.web.vm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.filrouge.gypsogest.domain.Sale;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record SaleResponseVM(
        Long id,
        LocalDateTime date,
        ClientResponseVM client,
        @JsonIgnoreProperties(value = { "sale" }, allowSetters = true)
        Set<ItemResponseVM> items
) {
    public static SaleResponseVM fromSale(Sale sale){
        return new SaleResponseVM(
                sale.getId(),
                sale.getDate(),
                ClientResponseVM.fromClient(sale.getClient()),
                sale.getItems().stream().map(ItemResponseVM::fromItem).collect(Collectors.toSet())
        );
    }
}

