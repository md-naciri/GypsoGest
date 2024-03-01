package com.filrouge.gypsogest.service;

import com.filrouge.gypsogest.domain.Returned;
import com.filrouge.gypsogest.domain.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ReturnedService {
    Returned saveReturned(Returned returned);
    Optional<Returned> findReturnedById(Long id);
    Optional<Returned> findReturnedByPaymentCodeAndClientId(String paymentCode, Long clientId);
    Set<Returned> findReturnedsByClientId(Long id);

    List<Returned> findAllReturneds();
    Returned updateReturned(Long id, Returned returned);
    void deleteReturned(Long id);
}
