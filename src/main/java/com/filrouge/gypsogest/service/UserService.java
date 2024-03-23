package com.filrouge.gypsogest.service;

import com.filrouge.gypsogest.domain.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
//    List<AppUser> getAllUsers();
//    AppUser getUserById(Long id);
//    AppUser saveUser(AppUser appUser);
    UserDetailsService userDetailsService();
    List<AppUser> getAllUsers();
    void deleteUser(String username);

}
