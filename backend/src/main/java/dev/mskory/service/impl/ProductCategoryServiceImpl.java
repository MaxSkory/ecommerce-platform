package dev.mskory.service.impl;

import jakarta.persistence.EntityNotFoundException;
import dev.mskory.dto.category.ProductCategoryRequestDto;
import dev.mskory.dto.category.ProductCategoryResponseDto;
import dev.mskory.mapper.ProductCategoryMapper;
import dev.mskory.repository.ProductCategoryRepository;
import dev.mskory.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository categoryRepository;
    private final ProductCategoryMapper categoryMapper;

    @Override
    public ProductCategoryResponseDto create(ProductCategoryRequestDto dto) {
        return categoryMapper.toDto(categoryRepository.save(categoryMapper.toEntity(dto)));
    }

    @Override
    public ProductCategoryResponseDto getById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Can't find ProductCategory by id " + id));
    }

    @Override
    public Page<ProductCategoryResponseDto> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable)
                .map(categoryMapper::toDto);
    }

    @Override
    @Transactional
    public ProductCategoryResponseDto updateById(Long id, ProductCategoryRequestDto dto) {
        return categoryRepository.findById(id)
                .map(e -> categoryMapper.update(dto, e))
                .map(categoryMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Can't find ProductCategory by id " + id));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
