package com.filrouge.gypsogest.web.vm;

import com.filrouge.gypsogest.domain.Item;
import com.filrouge.gypsogest.domain.enumeration.GypseType;
import jakarta.validation.constraints.NotNull;

public record ItemResponseVM(
        Long id,
        @NotNull Double quantity,
        @NotNull Double unitPrice,
        @NotNull GypseType gypseType
) {
    public static ItemResponseVM fromItem(Item item){
        return new ItemResponseVM(
                item.getId(),
                item.getQuantity(),
                item.getUnitPrice(),
                item.getGypseType()
        );
    }
}