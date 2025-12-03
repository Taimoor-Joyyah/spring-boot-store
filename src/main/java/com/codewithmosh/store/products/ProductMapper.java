package com.codewithmosh.store.products;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    com.codewithmosh.store.products.ProductDto toProductDto(Product product);

    Product toEntity(com.codewithmosh.store.products.ProductDto productDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(com.codewithmosh.store.products.ProductDto productDto, @MappingTarget Product product);

    com.codewithmosh.store.carts.ProductDto toInCartProductDto(Product product);
    com.codewithmosh.store.orders.ProductDto toInOrderProductDto(Product product);
}
