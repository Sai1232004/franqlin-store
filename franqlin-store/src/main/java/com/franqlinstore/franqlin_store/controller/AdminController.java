package com.franqlinstore.franqlin_store.controller;
import com.franqlinstore.franqlin_store.entity.AdminEntity;
import com.franqlinstore.franqlin_store.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")

public class AdminController {

    private final AdminService adminService;

    // Constructor injection
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> adminLogin(@RequestBody AdminEntity admin) {
        AdminEntity foundAdmin = adminService.getAdminByEmail(admin.getEmail());

        if (foundAdmin == null || !foundAdmin.getPassword().equals(admin.getPassword())) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Invalid email or password.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Login successful.");
        response.put("email", foundAdmin.getEmail());
        return ResponseEntity.ok(response);
    }
}
