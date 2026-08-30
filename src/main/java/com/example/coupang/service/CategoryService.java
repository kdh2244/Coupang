package com.example.coupang.service;

import com.example.coupang.entity.Category;
import com.example.coupang.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getCategoryList(){
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }




}
