package com.example.coupang.service;

import com.example.coupang.entity.Users;
import com.example.coupang.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;

    public void userInfoUpdate(Users user, Users newUserInfo){
        user.setName(newUserInfo.getName());
        user.setPhone(newUserInfo.getPhone());
        user.setAddress(newUserInfo.getAddress());

        usersRepository.save(user);
    }
}
