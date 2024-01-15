package com.filrouge.gypsogest.service;

import com.filrouge.gypsogest.domain.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleService {
    Sale createSale(Sale sale);
    Optional<Sale> findSaleById(Long id);
    List<Sale> findAllSales();
    Sale updateSale(Long id, Sale updatedSale);
    void deleteSale(Long id);
}
