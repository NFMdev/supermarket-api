package com.github.NFMdev.supermarket_api.controller;

import com.github.NFMdev.supermarket_api.dto.LoginRq;
import com.github.NFMdev.supermarket_api.dto.RegisterRq;
import com.github.NFMdev.supermarket_api.model.User;
import com.github.NFMdev.supermarket_api.service.AuthService;
import com.github.NFMdev.supermarket_api.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRq rq) {
        try {
            authService.register(rq.getEmail(), rq.getPassword(), rq.getPassword());
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error registering user: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRq rq) {
        try {
            User user = authService.login(rq.getEmail(), rq.getPassword());
            if (user != null) {
                String token = jwtService.generateToken(user.getEmail(), user.getUsername(), user.getRole());
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.badRequest().body("Invalid email or password");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error logging in: " + e.getMessage());
        }
    }
}
