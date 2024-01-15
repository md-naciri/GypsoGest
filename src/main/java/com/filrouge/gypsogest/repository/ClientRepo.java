package com.filrouge.gypsogest.repository;

import com.filrouge.gypsogest.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
    Optional<Client> findByCIN(String cin);
}
