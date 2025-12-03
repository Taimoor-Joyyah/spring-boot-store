package com.codewithmosh.store.orders;

import com.codewithmosh.store.products.ProductMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductMapper.class})
public interface OrderItemMapper {
    OrderItemDto toDto(OrderItem orderItem);
}
