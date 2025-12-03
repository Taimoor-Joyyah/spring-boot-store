package com.codewithmosh.store.carts;

import com.codewithmosh.store.products.Product;
import lombok.Data;

import java.math.BigDecimal;

/**
 * DTO for {@link Product}
 */
@Data
public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
}
