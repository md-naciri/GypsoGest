package com.filrouge.gypsogest.repository;

import com.filrouge.gypsogest.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findByPaymentCode(String paymentCode);
}
