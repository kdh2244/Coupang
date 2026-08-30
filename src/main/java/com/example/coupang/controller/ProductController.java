package com.example.coupang.controller;

import com.example.coupang.entity.Category;
import com.example.coupang.entity.Product;
import com.example.coupang.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.coupang.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService service;

    @Autowired
    CategoryService categoryService;


    @GetMapping("/search")
    public String search(@RequestParam String keyword, @RequestParam int page ,
                         @RequestParam String categoryId,
                         Model model){
        System.out.println("search 요청 들어옴 page 값 : "+page);
        System.out.println("categoryid 값은 : "+ categoryId);
        Page<Product> products = service.searchProducts(keyword,page,categoryId);
        model.addAttribute("products",products);
        model.addAttribute("keyword",keyword);

        List<Category> categoryList = categoryService.getCategoryList();
        model.addAttribute("categoryList",categoryList);

        String currentCategoryId = categoryId;
        model.addAttribute("currentCategoryId",currentCategoryId);
        return "home";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam int id,Model model){
        Product product = service.getProduct(id);
        model.addAttribute("product",product);
        return "productDetail";
    }


}
