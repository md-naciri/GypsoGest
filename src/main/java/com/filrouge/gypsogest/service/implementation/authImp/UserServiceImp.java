package com.filrouge.gypsogest.service.implementation.authImp;

import com.filrouge.gypsogest.domain.AppUser;
import com.filrouge.gypsogest.repository.UserRepo;
import com.filrouge.gypsogest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepo userRepo;
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService(){
            @Override
            public UserDetails loadUserByUsername(String username){
                return userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usern not found"));
            }
        };
    }

    @Override
    public List<AppUser> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public void deleteUser(String username) {
        userRepo.findByUsername(username).ifPresentOrElse(
                userRepo::delete,
                () -> {throw new UsernameNotFoundException("Employee not exist");}
        );
    }
}
