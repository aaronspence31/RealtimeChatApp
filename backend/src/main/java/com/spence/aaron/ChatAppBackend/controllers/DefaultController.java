package com.spence.aaron.ChatAppBackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DefaultController {

    @GetMapping("/login-success")
    public ResponseEntity<Map<String, Object>> loginSuccess(Principal principal) {
        // Prepare the response data
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("message", "Login Successful");
        responseData.put("username", principal.getName());

        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/public/logout-success")
    public ResponseEntity<Map<String, String>> logoutSuccess() {
        Map<String, String> responseMessage = new HashMap<>();
        responseMessage.put("message", "Logout Successful");

        return ResponseEntity.ok(responseMessage);
    }
}
