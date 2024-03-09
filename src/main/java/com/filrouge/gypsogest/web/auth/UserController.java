package com.filrouge.gypsogest.web.auth;

import com.filrouge.gypsogest.domain.AppUser;
import com.filrouge.gypsogest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
