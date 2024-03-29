package com.filrouge.gypsogest.service.implementation;
import com.filrouge.gypsogest.domain.Item;
import com.filrouge.gypsogest.domain.Returned;
import com.filrouge.gypsogest.domain.Sale;
import com.filrouge.gypsogest.domain.Transaction;
import com.filrouge.gypsogest.service.AccountingService;
import com.filrouge.gypsogest.service.ReturnedService;
import com.filrouge.gypsogest.service.SaleService;
import com.filrouge.gypsogest.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountingServiceImp implements AccountingService {
    private final SaleService saleService;
    private final TransactionService transactionService;
    private final ReturnedService returnedService;

    @Override
    @Transactional(readOnly = true)
    public double calculateCreditForClient(Long clientId) {
        Set<Sale> clientSales = saleService.findSalesByClientId(clientId);
        double totalCredit = 0.0;

        for (Sale sale : clientSales) {
            for (Item item : sale.getItems()) {
                totalCredit += item.getQuantity() * item.getUnitPrice();
            }
        }

        return totalCredit;
    }

    @Override
    @Transactional(readOnly = true)
    public double calculateDebitForClient(Long clientId) {
        Set<Transaction> clientTransactions = transactionService.findTransactionsByClientId(clientId);
        double totalDebit = 0.0;

        for (Transaction transaction : clientTransactions) {
            totalDebit += transaction.getAmount();
        }

        Set<Returned> clientReturneds = returnedService.findReturnedsByClientId(clientId);

        // Iterate over returneds and adjust totalDebit accordingly
        for (Returned returned : clientReturneds) {
            for (Transaction transaction : clientTransactions) {
                if (transaction.getPaymentCode().equals(returned.getPaymentCode())) {
                    totalDebit -= transaction.getAmount();
                    break; // Move to the next returned
                }
            }
        }
        return totalDebit;
    }

    @Override
    public double calculateTotalForClient(Long clientId) {
        double totalCredit = calculateCreditForClient(clientId);
        double totalDebit = calculateDebitForClient(clientId);

        return totalCredit - totalDebit;
    }

    @Override
    public double calculateCombinedCreditForClients(List<Long> clientIds) {
        return 0;
    }

    @Override
    public double calculateCombinedDebitForClients(List<Long> clientIds) {
        return 0;
    }

    @Override
    public double calculateCombinedTotalForClients(List<Long> clientIds) {
        return 0;
    }

    @Override
    public double calculateTotalCreditForAllClients() {
        return 0;
    }

    @Override
    public double calculateTotalDebitForAllClients() {
        return 0;
    }

    @Override
    public double calculateOverallTotalForAllClients() {
        return 0;
    }
}
