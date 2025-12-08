package com.gom.space_beauty.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gom.space_beauty.dto.Auth;
import com.gom.space_beauty.dto.LoginResponse;
import com.gom.space_beauty.dto.Register;
import com.gom.space_beauty.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody Auth data) {
        LoginResponse response = authService.login(data);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody Register data) {
        authService.register(data);
        return ResponseEntity.ok().build();
    }
}
