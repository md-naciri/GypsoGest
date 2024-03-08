package com.filrouge.gypsogest.database.seeder;

import com.filrouge.gypsogest.domain.AppUser;
import com.filrouge.gypsogest.domain.enumeration.Role;
import com.filrouge.gypsogest.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserSeeder {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    public void seed(){
        if(userRepository.count() == 0){
            List<AppUser> users = List.of(
                    AppUser.builder()
                            .name("John Doe")
                            .username("john123")
                            .role(Role.ROLE_USER)
                            .email("john123@gmail.com")
                            .password(passwordEncoder.encode("1234Password@!"))
                            .build(),
                    AppUser.builder()
                            .name("Jane Doe")
                            .username("jane456")
                            .role(Role.ROLE_ADMIN)
                            .email("jane456@gmail.com")
                            .password(passwordEncoder.encode("1234Password@!"))
                            .build(),
                    AppUser.builder()
                            .name("Mike Smith")
                            .username("mike789")
                            .role(Role.ROLE_ADMIN)
                            .email("mike798@gmail.com")
                            .password(passwordEncoder.encode("1234Password@!"))
                            .build(),
                    AppUser.builder()
                            .name("Linda Smith")
                            .username("linda101")
                            .role(Role.ROLE_USER)
                            .email("linda101@gmail.com")
                            .password(passwordEncoder.encode("1234Password@!"))
                            .build(),
                    AppUser.builder()
                            .name("David Smith")
                            .username("david202")
                            .role(Role.ROLE_USER)
                            .email("david201@gmail.com")
                            .password(passwordEncoder.encode("1234Password@!"))
                            .build()
            );
            userRepository.saveAll(users);
        }
    }
}