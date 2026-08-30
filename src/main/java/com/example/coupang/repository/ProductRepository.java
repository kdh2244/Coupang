package com.example.coupang.repository;

import com.example.coupang.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository <Product,Integer>{
    Page<Product> findByIdInAndNameContaining(List<Integer> ids , String name, Pageable pageable);
}
