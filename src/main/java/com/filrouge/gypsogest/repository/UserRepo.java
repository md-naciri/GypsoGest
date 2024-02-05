package com.filrouge.gypsogest.repository;

import com.filrouge.gypsogest.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
