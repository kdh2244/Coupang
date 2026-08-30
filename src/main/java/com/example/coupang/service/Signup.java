package com.example.coupang.service;

import com.example.coupang.DTO.UsersDto;
import com.example.coupang.entity.Users;
import com.example.coupang.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Signup {
    @Autowired
    UsersRepository usersRepository;

    public boolean duplicateCheck(Users users){
        System.out.println("user : " + users.getEmail());
        Optional<Users> users1 = usersRepository.findByEmail(users.getEmail());
        if (users1.isPresent()){
            return true;
        }else{
            usersRepository.save(users);
            return false;
        }
    }



}
