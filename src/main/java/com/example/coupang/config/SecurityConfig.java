package com.example.coupang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/","/login","/signup").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/home","/product/**","/order/**","/cart","/mypage/**").hasAnyRole("USER","ADMIN")
                )
                .formLogin(form->form
                        .loginPage("/login")
                        .successHandler(((request, response,authentication) -> {

                            if(authentication.getAuthorities().stream()
                                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))){
                                System.out.println("admin 계쩡 맞네 이거 ㅋㅋ");
                                response.sendRedirect("/admin");
                            }else{
                                System.out.println("admin 계쩡 아니데 하.. ");
                                response.sendRedirect("/home");
                            }
                        }))
                );
        return http.build();
    }
}
