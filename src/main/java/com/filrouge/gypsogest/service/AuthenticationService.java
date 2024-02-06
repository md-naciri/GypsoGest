package com.filrouge.gypsogest.service;

import com.filrouge.gypsogest.security.dao.request.SignInRequest;
import com.filrouge.gypsogest.security.dao.request.SignUpRequest;
import com.filrouge.gypsogest.security.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signUp(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signIn(SignInRequest signInRequest);
}
