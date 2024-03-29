package com.filrouge.gypsogest.repository;

import com.filrouge.gypsogest.domain.Returned;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ReturnedRepo extends JpaRepository<Returned, Long> {
    Optional <Returned> findByPaymentCode(String paymentCode);
    Optional <Returned> findByPaymentCodeAndClientId(String paymentCode, Long clientID);
    Set<Returned> findByClientId(Long id);
}
