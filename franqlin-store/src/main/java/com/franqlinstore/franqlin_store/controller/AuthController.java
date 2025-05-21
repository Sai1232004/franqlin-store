package com.franqlinstore.franqlin_store.controller;
import com.franqlinstore.franqlin_store.entity.UserEntity;
import com.franqlinstore.franqlin_store.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        UserEntity user = userService.findByEmail(loginRequest.getEmail());

        if (user == null) {
            return ResponseEntity.status(401).body("User not found");
        }

        // For demo: simple password check (hashing recommended for production)
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(401).body("Invalid password");
        }

        // On success, return user details or token (simplified here)
        return ResponseEntity.ok(user);
    }

    // DTO class for login request
    @Setter
    @Getter
    public static class LoginRequest {
        // Getters and setters
        private String email;
        private String password;

    }
}
