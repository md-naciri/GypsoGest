package com.filrouge.gypsogest.service.implementation;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest signUpRequest) {
        var user = AppUser.builder()
                .name(signUpRequest.getName())
                .username(signUpRequest.getUsername())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .role(Role.USER)
                .build();
        userRepo.save(user);
        var token = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(token).build();
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
        var user = userRepo.findByUsername(signInRequest.getUsername()).orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
