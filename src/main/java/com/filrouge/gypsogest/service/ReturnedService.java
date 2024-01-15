package com.filrouge.gypsogest.service;

import com.filrouge.gypsogest.domain.Returned;

import java.util.List;
import java.util.Optional;

public interface ReturnedService {
    Returned saveReturned(Returned returned);
    Optional<Returned> findReturnedById(Long id);
    List<Returned> findAllReturneds();
    Returned updateReturned(Long id, Returned returned);
    void deleteReturned(Long id);
}
