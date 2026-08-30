package com.example.coupang.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String status;

    private int totalPrice;

    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;


}
