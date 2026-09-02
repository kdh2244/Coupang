package com.example.coupang.DTO;

import com.example.coupang.entity.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@NoArgsConstructor
public class UsersDto {

    @NotBlank(message = "유저명은 필수입니다.")
    private String name;

    @Email(message = "email 형식이어야 합니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$",
    message = "휴대폰 번호 입력해주세요.")
    private String phone;

    @NotBlank(message = "주소는 필수입니다.")
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
