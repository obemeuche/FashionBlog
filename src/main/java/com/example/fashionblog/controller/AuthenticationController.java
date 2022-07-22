package com.example.fashionblog.controller;

import com.example.fashionblog.dto.LoginDto;
import com.example.fashionblog.dto.SignupDto;
import com.example.fashionblog.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class AuthenticationController {

    private final CustomerService customerService;

    @PostMapping("/signUp")
    public String signup(@RequestBody SignupDto signupDto){
        customerService.signup(signupDto);
        return "Signup successful";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(customerService.login(loginDto));
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(){
        return ResponseEntity.ok(customerService.logout());
    }
}
