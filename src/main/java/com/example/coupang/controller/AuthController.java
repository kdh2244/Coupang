package com.example.coupang.controller;

import com.example.coupang.DTO.UsersDto;
import com.example.coupang.entity.Users;
import com.example.coupang.service.Signup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    @Autowired
    Signup signup;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/signup")
    public String signup(Model model, UsersDto usersDto) {
        System.out.println("userdto email : "+usersDto.getEmail());
        Users users = usersDto.toEntity(passwordEncoder);
        if (signup.duplicateCheck(users)) {
            return "signup";
        } else {
            return "home";
        }
    }
}
