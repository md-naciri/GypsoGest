package com.filrouge.gypsogest.repository;

import com.filrouge.gypsogest.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepo extends JpaRepository<Sale, Long> {
}
