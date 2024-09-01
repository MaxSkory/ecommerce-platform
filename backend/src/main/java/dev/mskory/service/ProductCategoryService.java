package dev.mskory.service;

import java.util.List;
import dev.mskory.dto.category.ProductCategoryRequestDto;
import dev.mskory.dto.category.ProductCategoryResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductCategoryService {

    List<ProductCategoryResponseDto> getAll();

    ProductCategoryResponseDto getById(Long id);

    ProductCategoryResponseDto create(ProductCategoryRequestDto dto);

    ProductCategoryResponseDto updateById(Long id, ProductCategoryRequestDto dto);

    void deleteById(Long id);

}
