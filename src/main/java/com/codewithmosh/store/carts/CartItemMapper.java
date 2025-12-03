package com.codewithmosh.store.carts;

import com.codewithmosh.store.products.ProductMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductMapper.class})
public interface CartItemMapper {
    CartItemDto toDto(CartItem cartItem);
}
