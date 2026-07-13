package com.ngo.ngo_event_management.controller;

import com.ngo.ngo_event_management.entity.User;
import com.ngo.ngo_event_management.repository.UserRepository;
import com.ngo.ngo_event_management.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "https://impactpulseorg.netlify.app", allowCredentials = "true")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }
    @GetMapping("/")
public ResponseEntity<Map<String, String>> homeHealthCheck() {
    return ResponseEntity.ok(Map.of(
        "status", "UP",
        "message", "NGO Management Backend API is running successfully!"
    ));
}
@PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("message", "Email address is already registered!"));
            }
            
            // FIX IS RIGHT HERE: Make sure 'User' is added at the front
            User registeredUser = userService.registerNewUser(user);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Registration error: " + e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String plainPassword = credentials.get("password");

        // 1. HARDCODED FIX FOR ADMIN (Bypasses all DB errors)
        if ("yukeshadmin@gmail.com".equals(email) && "admin123456".equals(plainPassword)) {
            User adminOverride = new User();
            adminOverride.setEmail("yukeshadmin@gmail.com");
            adminOverride.setFullName("Yukesh Admin");
            adminOverride.setRole("ADMIN");
            return ResponseEntity.ok(adminOverride);
        }

        // 2. Fetch from DB for regular users
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid email or password"));
        }

        // 3. BULLETPROOF PASSWORD CHECK (Checks BCrypt HASH first, then falls back to PLAIN TEXT)
        boolean matchesBCrypt = false;
        try {
            matchesBCrypt = passwordEncoder.matches(plainPassword, user.getPassword());
        } catch (Exception e) {
            // Ignore BCrypt parsing exception if database contains plain text
        }

        boolean matchesPlainText = plainPassword.equals(user.getPassword());

        if (matchesBCrypt || matchesPlainText) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid email or password"));
        }
    }
}