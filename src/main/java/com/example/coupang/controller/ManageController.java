package com.example.coupang.controller;

import com.example.coupang.DTO.ProductDto;
import com.example.coupang.entity.Category;
import com.example.coupang.entity.Product;
import com.example.coupang.service.CategoryService;
import com.example.coupang.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ManageController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String manageHome(){
        return "manage";
    }

    @GetMapping("/add")
    public String showAddProduct(Model model){

        List<Category> categoryList = categoryService.getCategoryList();
        model.addAttribute("categoryList",categoryList);

        return "addProduct";
    }

    @PostMapping("/add")
    public String addProduct(Model model, @Valid ProductDto productDto,
                             @RequestParam List<Integer> addCategoryIds){

        Product product = productDto.toEntity();
        productService.saveWithCategories(product,addCategoryIds);

        return "addProduct";
    }


    @RequestMapping("/edit/search")
    public String pageMoveEditProduct(Model model,
                                  @RequestParam int pageNumber,
                                      @RequestParam String keyword,
                                      @RequestParam String categoryId){

        System.out.println("categoryid 값은 : "+ categoryId);

        Page<Product> products = productService.searchProducts(keyword,pageNumber,categoryId);
        model.addAttribute("products",products);
        model.addAttribute("keyword",keyword);

        List<Category> categoryList = categoryService.getCategoryList();
        model.addAttribute("categoryList",categoryList);

        String currentCategoryId = categoryId;
        model.addAttribute("currentCategoryId",currentCategoryId);

        model.addAttribute("pageNumber",pageNumber);


        return "editProduct";
    }

    @PostMapping(value = "/edit")
    public String editProduct(Model model,
                              @Valid ProductDto productDto,
                              @RequestParam("productId") int productId,
                              @RequestParam int pageNumber,
                              @RequestParam String keyword,
                              @RequestParam String categoryId,
                              @RequestParam List<Integer> addCategoryIds){

        Product product = productDto.toEntity();
        productService.updateProduct(product,productId,addCategoryIds);


        return "redirect:/admin/edit/search?pageNumber="+pageNumber+"&keyword="+keyword+"&categoryId="+categoryId;
    }



}
