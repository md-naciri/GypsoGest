package com.filrouge.gypsogest.web.resource;

import com.filrouge.gypsogest.domain.Client;
import com.filrouge.gypsogest.domain.Returned;
import com.filrouge.gypsogest.domain.Sale;
import com.filrouge.gypsogest.domain.Transaction;
import com.filrouge.gypsogest.exception.ResponseHandler;
import com.filrouge.gypsogest.service.*;
import com.filrouge.gypsogest.web.vm.ClientResponseVM;

import com.filrouge.gypsogest.web.vm.CreditResponseVM;
import com.filrouge.gypsogest.web.vm.DebitResponseVM;
import com.filrouge.gypsogest.web.vm.TotalResponseVM;
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
    private final TransactionService transactionService;
    private final ReturnedService returnedService;

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

    @GetMapping("/client-debit/{clientId}")
    public ResponseEntity<?> getClientDebit(@PathVariable Long clientId) {
        Optional<Client> clientOptional = clientService.findClientById(clientId);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            Set<Transaction> clientTransactions = transactionService.findTransactionsByClientId(clientId);
            Set<Returned> clientReturneds = returnedService.findReturnedsByClientId(clientId);
            Double debit = accountingService.calculateDebitForClient(clientId);

            DebitResponseVM debitResponse = DebitResponseVM.fromClientAndTransactions(client, new ArrayList<>(clientTransactions), new ArrayList<>(clientReturneds), debit);
            return ResponseHandler.ok(debitResponse, "Client debit retrieved successfully.");
        } else {
            return ResponseHandler.notFound("Client not found with id: " + clientId);
        }
    }

    @GetMapping("/client-total/{clientId}")
    public ResponseEntity<?> getClientTotal(@PathVariable Long clientId) {
        Optional<Client> clientOptional = clientService.findClientById(clientId);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            double total = accountingService.calculateTotalForClient(clientId);

            TotalResponseVM totalResponse = TotalResponseVM.fromClient(client, total);
            return ResponseHandler.ok(totalResponse, "Client total calculated successfully.");
        } else {
            return ResponseHandler.notFound("Client not found with id: " + clientId);
        }
    }

}