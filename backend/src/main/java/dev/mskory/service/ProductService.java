package dev.mskory.service;

import dev.mskory.dto.product.ProductRequestDto;
import dev.mskory.dto.product.ProductResponseDto;
import dev.mskory.dto.product.ProductSpecificationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ProductResponseDto> getAll(Pageable pageable);

    ProductResponseDto getById(Long id);

    ProductResponseDto create(ProductRequestDto dto);

    ProductResponseDto updateById(Long id, ProductRequestDto dto);

    void deleteById(Long id);

    Page<ProductResponseDto> getProductsByCategoryId(Long categoryId, Pageable pageable);

    Page<ProductResponseDto> search(ProductSpecificationDto dto, Pageable pageable);
}
