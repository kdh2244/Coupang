package com.example.coupang.repository;

import com.example.coupang.entity.Orderitem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderitemRepository extends JpaRepository<Orderitem,Integer> {
   List<Orderitem> findByOrder_id(int orderId);
}
