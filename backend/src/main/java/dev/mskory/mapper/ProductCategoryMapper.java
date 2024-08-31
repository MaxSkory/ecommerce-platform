package dev.mskory.mapper;

import dev.mskory.config.MapperConfig;
import dev.mskory.dto.category.ProductCategoryRequestDto;
import dev.mskory.dto.category.ProductCategoryResponseDto;
import dev.mskory.entity.ProductCategory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface ProductCategoryMapper {
    ProductCategory toEntity(ProductCategoryRequestDto dto);

    ProductCategoryResponseDto toDto(ProductCategory entity);

    ProductCategory update(ProductCategoryRequestDto dto, @MappingTarget ProductCategory entity);
}