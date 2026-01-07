package com.example.notification_service.controller;

import com.example.notification_service.model.User;
import com.example.notification_service.repository.UserRepository;
import com.example.notification_service.security.JwtUtil;
import com.example.notification_service.service.UserService;
import com.example.notification_service.dto.AuthRequest;
import com.example.notification_service.dto.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    // ✅ normal constructor (no Lombok needed)
    public AuthController(UserService userService,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          UserRepository userRepository) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {

        User user = userService.register(
                request.getUsername(),
                request.getPassword()
        );

        return ResponseEntity.ok("User registered: " + user.getUsername());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        // authenticate username/password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // load user & roles
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Set<String> roles = user.getRoles().stream()
                .map(Enum::name)                   // Role -> "ROLE_USER"
                .collect(Collectors.toSet());

        // generate JWT
        String token = jwtUtil.generateToken(user.getUsername(), roles);

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
