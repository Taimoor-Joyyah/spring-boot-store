package com.codewithmosh.store.carts;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO for {@link CartItem}
 */
@Data
public class AddCartItemDto {
    @NotNull
    private Long productId;
}
