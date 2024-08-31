package dev.mskory.mapper;

import dev.mskory.config.MapperConfig;
import dev.mskory.dto.product.ProductRequestDto;
import dev.mskory.dto.product.ProductResponseDto;
import dev.mskory.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface ProductMapper {
    Product toEntity(ProductRequestDto dto);

    @Mapping(target = "categoryId", source = "category.id")
    ProductResponseDto toDto(Product entity);

    Product update(ProductRequestDto dto, @MappingTarget Product entity);
}
