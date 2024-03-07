package com.filrouge.gypsogest.web.auth;

import com.filrouge.gypsogest.security.dao.request.SignInRequest;
import com.filrouge.gypsogest.security.dao.request.SignUpRequest;
import com.filrouge.gypsogest.security.dao.response.JwtAuthenticationResponse;
import com.filrouge.gypsogest.service.AuthenticationService;
import com.filrouge.gypsogest.service.JwtService;
import com.filrouge.gypsogest.service.UserService;
import com.filrouge.gypsogest.web.vm.TokenValidationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final JwtService jwtService;
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }

    @PostMapping("/signing")
    public ResponseEntity<JwtAuthenticationResponse> signing(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }
    @PostMapping("/validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestBody TokenValidationRequest validationRequest) {
        String token = validationRequest.getToken();

        try {
            UserDetails userDetails = userService.userDetailsService()
                    .loadUserByUsername(jwtService.extractUserName(token));

            if (jwtService.isTokenValid(token, userDetails)) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.ok(false);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.ok(false);
        }
    }
}
