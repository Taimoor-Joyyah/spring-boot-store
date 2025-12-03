package com.codewithmosh.store.carts;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * DTO for {@link CartItem}
 */
@Data
public class UpdateCartItemDto {
    @Range(min = 1, max = 100, message = "Quantity must be between 1 - 100.")
    private Integer quantity;
}
