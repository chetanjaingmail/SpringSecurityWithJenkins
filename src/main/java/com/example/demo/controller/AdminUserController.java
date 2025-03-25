package com.example.demo.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;

import jakarta.validation.Valid;

@RestController
public class AdminUserController {
	
	 private final UserRepository userRepository;
	    private final PasswordEncoder passwordEncoder;

	    public AdminUserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
	        this.userRepository = userRepository;
	        this.passwordEncoder = passwordEncoder;
	    }

    @GetMapping("/admin")
    public String adminPage() {
        return "Welcome, Admin!";
    }

    @GetMapping("/user")
    public String userPage() {
        return "Welcome, User!";
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user , BindingResult result) {
        // Encrypt the password before saving
    	
    	 if (result.hasErrors()) {
             Map<String, String> errors = new HashMap<>();
             for (FieldError error : result.getFieldErrors()) {
                 errors.put(error.getField(), error.getDefaultMessage());
             }
             return ResponseEntity.badRequest().body(errors);
         }
    	
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        userRepository.save(user);
        return ResponseEntity.ok("User saved successfully!");
    }
    
}

