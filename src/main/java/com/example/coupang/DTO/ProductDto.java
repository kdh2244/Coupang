package com.example.coupang.DTO;

import com.example.coupang.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

    private String name;

    private int price;

    private String description;

    private int stock;

    public Product toEntity(){
        return Product.builder()
                .name(name)
                .price(price)
                .description(description)
                .stock(stock)
                .build();
    }
}
