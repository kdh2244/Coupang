package com.example.coupang.controller;

import com.example.coupang.DTO.UsersDto;
import com.example.coupang.entity.Orderitem;
import com.example.coupang.entity.Users;
import com.example.coupang.repository.UsersRepository;
import com.example.coupang.service.OrderService;
import com.example.coupang.service.UsersService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Controller()
@RequestMapping("/mypage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyPageController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UsersService usersService;

    @Autowired
    OrderService orderService;

    @GetMapping()
    public String myPage(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Users> optionalUser = usersRepository.findByEmail(authentication.getName());
        Users user = optionalUser.get();

        model.addAttribute("user",user);
        model.addAttribute("mode","info");
        return "mypage";
    }

    @PostMapping("/update")
    public String updateUserInfo(Model model, UsersDto usersDto){

        Users newUserInfo = usersDto.toEntity(passwordEncoder);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Users> optionalUser = usersRepository.findByEmail(authentication.getName());
        Users user = optionalUser.get();

        usersService.userInfoUpdate(user,newUserInfo);

        return "redirect:/home";
    }

    @GetMapping("/recentOrder")
    public String recentOrder(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Users> optionalUser = usersRepository.findByEmail(authentication.getName());
        Users user = optionalUser.get();

        List<List<Orderitem>> orderitemsList = orderService.getOrderitems(user);
        //System.out.println("orderitesList : " + orderitemsList);
        model.addAttribute("orderitemsList",orderitemsList);

        model.addAttribute("mode","order");
        return "myPage";
    }


}
