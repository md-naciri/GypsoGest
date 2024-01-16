package com.filrouge.gypsogest.repository;

import com.filrouge.gypsogest.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SaleRepo extends JpaRepository<Sale, Long> {
    Set<Sale>  findByClient_Id(Long id);
}
