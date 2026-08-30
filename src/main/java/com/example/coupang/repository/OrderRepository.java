package com.example.coupang.repository;

import com.example.coupang.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    Order findByUsers_idAndStatusStartsWith(int users_id,String status);
    List<Order> findByUsers_id(int users_id);
}
