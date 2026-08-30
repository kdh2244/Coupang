package com.example.coupang.controller;

import com.example.coupang.entity.Category;
import com.example.coupang.entity.Product;
import com.example.coupang.service.CategoryService;
import com.example.coupang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public String root(){
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model){
        Page<Product> products = productService.list();
        model.addAttribute("products",products);

        List<Category> categoryList = categoryService.getCategoryList();
        model.addAttribute("categoryList",categoryList);

        String currentCategoryId = "";
        model.addAttribute("currentCategoryId",currentCategoryId);

        return "home";
    }


}
