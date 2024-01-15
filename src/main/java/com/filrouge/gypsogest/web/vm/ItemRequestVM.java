package com.filrouge.gypsogest.web.vm;

import com.filrouge.gypsogest.domain.Item;
import com.filrouge.gypsogest.domain.Sale;
import com.filrouge.gypsogest.domain.enumeration.GypseType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemRequestVM(
        @NotNull(message = "Quantity cannot be null")
        @Positive(message = "Quantity must be a positive number")
        Double quantity,
        @NotNull(message = "Unit price cannot be null")
        @Positive(message = "Unit price must be a positive number")
        Double unitPrice,
        @NotNull(message = "Gypse type cannot be null")
        GypseType gypseType
) {
    public Item toItem(Sale sale) {
        return Item.builder()
                .quantity(quantity)
                .unitPrice(unitPrice)
                .gypseType(gypseType)
                .sale(sale)
                .build();
    }
}

