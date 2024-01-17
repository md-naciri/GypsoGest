package com.filrouge.gypsogest.web.resource;

import com.filrouge.gypsogest.domain.Sale;
import com.filrouge.gypsogest.exception.ResponseHandler;
import com.filrouge.gypsogest.service.SaleService;
import com.filrouge.gypsogest.web.vm.SaleRequestVM;
import com.filrouge.gypsogest.web.vm.SaleResponseVM;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
public class SaleResource {

    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<?> createSale(@Valid @RequestBody SaleRequestVM saleRequest) {
        Sale sale = saleRequest.toSale();
        sale = saleService.createSale(sale);
        SaleResponseVM saleResponse = SaleResponseVM.fromSale(sale);
        return ResponseHandler.created(saleResponse, "Sale created successfully.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSaleById(@PathVariable Long id) {
        return saleService.findSaleById(id)
                .map(sale -> ResponseHandler.ok(SaleResponseVM.fromSale(sale), "Sale found."))
                .orElse(ResponseHandler.notFound("Sale not found."));
    }

    @GetMapping
    public ResponseEntity<?> getAllSales() {
        List<Sale> sales = saleService.findAllSales();
        List<SaleResponseVM> saleResponses = sales.stream()
                .map(SaleResponseVM::fromSale)
                .collect(Collectors.toList());
        return ResponseHandler.ok(saleResponses, "All sales retrieved successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSale(@PathVariable Long id, @Valid @RequestBody SaleRequestVM saleRequest) {
        Sale updatedSale = saleRequest.toSale();
        Sale sale = saleService.updateSale(id, updatedSale);
        SaleResponseVM saleResponse = SaleResponseVM.fromSale(sale);
        return ResponseHandler.ok(saleResponse, "Sale updated successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseHandler.ok(null, "Sale deleted successfully.");
    }
}
