package dev.mskory.service.impl;

import jakarta.persistence.EntityNotFoundException;
import dev.mskory.dto.product.ProductRequestDto;
import dev.mskory.dto.product.ProductResponseDto;
import dev.mskory.entity.Product;
import dev.mskory.entity.ProductCategory;
import dev.mskory.mapper.ProductMapper;
import dev.mskory.repository.ProductCategoryRepository;
import dev.mskory.repository.ProductRepository;
import dev.mskory.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public ProductResponseDto create(ProductRequestDto dto) {
        ProductCategory referenceCategory = categoryRepository.getReferenceById(dto.categoryId());
        Product productModel = productMapper.toEntity(dto);
        productModel.setCategory(referenceCategory);
        return productMapper.toDto(productRepository.save(productModel));
    }

    @Override
    public ProductResponseDto getById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Can't find Product by id " + id));
    }

    @Override
    public Page<ProductResponseDto> getAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toDto);
    }

    @Override
    public ProductResponseDto updateById(Long id, ProductRequestDto dto) {
        return productRepository.findById(id)
                .map(e -> productMapper.update(dto, e))
                .map(productRepository::save)
                .map(productMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Can't find Product by id " + id));
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<ProductResponseDto> getProductsByCategoryId(Long categoryId, Pageable pageable) {
        return productRepository.findAllByCategoryId(categoryId, pageable)
                .map(productMapper::toDto);
    }
}
