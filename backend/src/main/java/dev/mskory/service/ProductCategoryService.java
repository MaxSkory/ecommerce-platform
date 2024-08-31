package dev.mskory.service;

import dev.mskory.dto.category.ProductCategoryRequestDto;
import dev.mskory.dto.category.ProductCategoryResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductCategoryService {

    Page<ProductCategoryResponseDto> getAll(Pageable pageable);

    ProductCategoryResponseDto getById(Long id);

    ProductCategoryResponseDto create(ProductCategoryRequestDto dto);

    ProductCategoryResponseDto updateById(Long id, ProductCategoryRequestDto dto);

    void deleteById(Long id);

}
