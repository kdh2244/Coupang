package com.example.coupang.DTO;

import com.example.coupang.entity.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@NoArgsConstructor
public class UsersDto {

    private String name;

    private String email;

    private String password;

    private String phone;

    private String address;

    public Users toEntity(PasswordEncoder encoder){
        return Users.builder()
                .name(name)
                .email(email)
                .password(encoder.encode(password))
                .phone(phone)
                .address(address)
                .role("USER")
                .build();
    }
}
