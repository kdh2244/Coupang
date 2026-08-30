package com.example.coupang.service;

import com.example.coupang.entity.Users;
import com.example.coupang.repository.UsersRepository;
import org.hibernate.sql.ast.tree.from.CorrelatedTableGroup;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository repository;

    public CustomUserDetailsService(UsersRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email){
        Users user = repository.findByEmail(email)
                .orElseThrow();

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }

}
