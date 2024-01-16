package com.filrouge.gypsogest.service.implementation;
import com.filrouge.gypsogest.domain.Sale;
import com.filrouge.gypsogest.service.AccountingService;
import com.filrouge.gypsogest.service.SaleService;
import com.filrouge.gypsogest.service.TransactionService;
import com.filrouge.gypsogest.web.vm.CreditResponseVM;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountingServiceImp implements AccountingService {
    private final SaleService saleService;
    private final TransactionService transactionService;
    @Override
    public List<CreditResponseVM> calculateCreditForClient(Long clientId) {
        // Fetch sales for the client
        Set<Sale> sales = saleService.findSalesByClientId(clientId);

        // Convert each sale to CreditResponseVM using the static factory method
        return sales.stream().map(CreditResponseVM::fromSale).collect(Collectors.toList());
    }

    @Override
    public double calculateDebitForClient(Long clientId) {
        return 0;
    }

    @Override
    public double calculateTotalForClient(Long clientId) {
        return 0;
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
