package com.filrouge.gypsogest.web.resource;

import com.filrouge.gypsogest.domain.Client;
import com.filrouge.gypsogest.domain.Sale;
import com.filrouge.gypsogest.exception.ResponseHandler;
import com.filrouge.gypsogest.service.AccountingService;
import com.filrouge.gypsogest.service.ClientService;
import com.filrouge.gypsogest.service.SaleService;
import com.filrouge.gypsogest.web.vm.ClientResponseVM;

import com.filrouge.gypsogest.web.vm.CreditResponseVM;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/accounting")
@RequiredArgsConstructor
public class AccountingResource {

    private final AccountingService accountingService;
    private final ClientService clientService;
    private final SaleService saleService;

    @GetMapping("/client-credit/{clientId}")
    public ResponseEntity<?> getClientCredit(@PathVariable Long clientId) {
        Optional<Client> clientOptional = clientService.findClientById(clientId);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            Set<Sale> clientSales = saleService.findSalesByClientId(clientId);
            Double credit = accountingService.calculateCreditForClient(clientId);

            CreditResponseVM creditResponse = CreditResponseVM.fromClientAndSales(client, new ArrayList<>(clientSales), credit);
            return ResponseHandler.ok(creditResponse, "Client credit retrieved successfully.");
        } else {
            return ResponseHandler.notFound("Client not found with id: " + clientId);
        }
    }
}
