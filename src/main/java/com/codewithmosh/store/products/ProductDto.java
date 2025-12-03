package com.codewithmosh.store.products;

import lombok.Data;

import java.math.BigDecimal;

/**
 * DTO for {@link Product}
 */
@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Byte categoryId;
}
