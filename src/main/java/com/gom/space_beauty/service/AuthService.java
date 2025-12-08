package com.gom.space_beauty.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gom.space_beauty.dto.Auth;
import com.gom.space_beauty.dto.LoginResponse;
import com.gom.space_beauty.dto.Register;
import com.gom.space_beauty.entity.User;
import com.gom.space_beauty.repository.UserRepository;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository,
            TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(Auth data) {
        var authToken = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
        var auth = authenticationManager.authenticate(authToken);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return new LoginResponse(token);
    }

    public void register(Register data) {
        if (userRepository.findByUsername(data.getUsername()) != null) {
            throw new RuntimeException("User already exists.");
        }

        String encryptedPassword = passwordEncoder.encode(data.getPassword());
        User newUser = User.builder()
                .username(data.getUsername())
                .email(data.getEmail())
                .password(encryptedPassword)
                .build();
        userRepository.save(newUser);
    }
}
