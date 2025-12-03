package com.codewithmosh.store.carts;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {CartItemMapper.class})
public interface CartMapper {
    CartDto toDto(Cart cart);
}
