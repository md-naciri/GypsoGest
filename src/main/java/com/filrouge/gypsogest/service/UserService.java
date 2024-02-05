package com.filrouge.gypsogest.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
//    List<AppUser> getAllUsers();
//    AppUser getUserById(Long id);
//    AppUser saveUser(AppUser appUser);
    UserDetailsService userDetailsService();
}
