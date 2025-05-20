package com.github.NFMdev.supermarket_api.service;

import com.github.NFMdev.supermarket_api.model.User;
import com.github.NFMdev.supermarket_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(String email, String password, String username) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);
        userRepository.save(user);
    }

    public User login(String email, String password) {
        if (userRepository.findByEmail(email).isEmpty()) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        User user = userRepository.findByEmail(email).get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return user;
    }
}
