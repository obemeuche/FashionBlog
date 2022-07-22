package com.example.fashionblog.service;

import com.example.fashionblog.dto.LoginDto;
import com.example.fashionblog.dto.SignupDto;

public interface CustomerService {

    void signup(SignupDto signupDto);

    String login(LoginDto loginDto);

    String logout();

//    User findUserById(Long userId);
}
