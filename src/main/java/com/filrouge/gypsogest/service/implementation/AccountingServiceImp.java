package com.filrouge.gypsogest.service.implementation;
import com.filrouge.gypsogest.service.AccountingService;
import com.filrouge.gypsogest.service.SaleService;
import com.filrouge.gypsogest.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class AccountingServiceImp implements AccountingService {
    private final SaleService saleService;
    private final TransactionService transactionService;
    @Override
    public double calculateCreditForClient(Long clientId) {
        return 0;
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
