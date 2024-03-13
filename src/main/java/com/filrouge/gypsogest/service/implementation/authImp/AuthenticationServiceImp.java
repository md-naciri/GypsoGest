package com.filrouge.gypsogest.service.implementation.authImp;

import com.filrouge.gypsogest.domain.AppUser;
import com.filrouge.gypsogest.domain.enumeration.Role;
import com.filrouge.gypsogest.repository.UserRepo;
import com.filrouge.gypsogest.security.dao.request.SignInRequest;
import com.filrouge.gypsogest.security.dao.request.SignUpRequest;
import com.filrouge.gypsogest.security.dao.response.JwtAuthenticationResponse;
import com.filrouge.gypsogest.service.AuthenticationService;
import com.filrouge.gypsogest.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
//    @Override
//    public JwtAuthenticationResponse signUp(SignUpRequest signUpRequest) {
//        var user = AppUser.builder()
//                .name(signUpRequest.getName())
//                .username(signUpRequest.getUsername())
//                .password(passwordEncoder.encode(signUpRequest.getPassword()))
//                .role(signUpRequest.getRole())
//                .build();
//        userRepo.save(user);
//        var token = jwtService.generateToken(user);
//        return JwtAuthenticationResponse.builder().token(token).name(signUpRequest.getName()).build();
//    }

    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest signUpRequest) {
        userRepo.findByUsername(signUpRequest.getUsername()).ifPresent(user -> {
            throw new IllegalArgumentException("username already exist");
        });
        try{
            Role role =  Role.valueOf(String.valueOf(signUpRequest.getRole()));
            var user = AppUser.builder()
                    .name(signUpRequest.getName())
                    .username(signUpRequest.getUsername())
                    .password(passwordEncoder.encode(signUpRequest.getPassword()))
                    .role(role)
                    .build();
            userRepo.save(user);
            var token = jwtService.generateToken(user);
            return JwtAuthenticationResponse.builder().token(token).name(signUpRequest.getName()).build();
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("role not exist");
        }


    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {

        try{
            AppUser user = userRepo.findByUsername(signInRequest.getUsername()).orElseThrow(() -> new UsernameNotFoundException("username not exist "));
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
            var jwt = jwtService.generateToken(user);
            return JwtAuthenticationResponse.builder().token(jwt).name(user.getName()).role(user.getRole().toString()).build();
        }catch(AuthenticationException e){
            throw new IllegalArgumentException("invalid email or password");
        }
    }
}
