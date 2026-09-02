package com.example.coupang.DTO;

import com.example.coupang.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

    @NotBlank(message = "상품명은 필수입니다.")
    private String name;

    @NotNull(message = "가격은 필수입니다.")
    @Positive(message = "가격은 0 보다 커야합니다.")
    private int price;


    private String description;

    @NotNull(message = "제고는 필수입니다.")
    @PositiveOrZero(message = "제고는 0 이상이어야 합니다.")
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
