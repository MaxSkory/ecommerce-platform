package dev.mskory.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import jakarta.persistence.metamodel.EntityType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import dev.mskory.dto.product.ProductRequestDto;
import dev.mskory.dto.product.ProductResponseDto;
import dev.mskory.dto.product.ProductSpecificationDto;
import dev.mskory.entity.Product;
import dev.mskory.entity.ProductCategory;
import dev.mskory.mapper.ProductMapper;
import dev.mskory.repository.ProductCategoryRepository;
import dev.mskory.repository.ProductRepository;
import dev.mskory.repository.specification.SpecBuilder;
import dev.mskory.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository categoryRepository;
    private final SpecBuilder<Product> specBuilder;
    private final ProductMapper productMapper;
    private final EntityManager entityManager;

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

    @Override
    public Page<ProductResponseDto> search(ProductSpecificationDto dto, Pageable pageable) {
        return productRepository.findAll(specBuilder.build(dto), pageable)
                .map(productMapper::toDto);
    }

    private List<ProductResponseDto> keywordsCombinedSearch(String[] keywords) {
        if (keywords != null && keywords.length > 0) {
            String nameMatchQuery = "select * from products where 1=1";
            String nameLikeQuery = "select * from products where 1=0";
            String descriptionQuery = "select * from products where 1=0";
            for (String keyword : keywords) {
                nameMatchQuery = nameMatchQuery.concat(" and name like '%" + keyword + "%'");
                nameLikeQuery = nameLikeQuery.concat(" or name like '%" + keyword + "%'");
                descriptionQuery = descriptionQuery.concat(" or description like '%" + keyword + "%'");
            }
            String resultQuery = nameMatchQuery + " union " + nameLikeQuery + " union " + descriptionQuery;
            return entityManager.createNativeQuery(resultQuery, Product.class).getResultList();
        }
        return Collections.emptyList();
    }
}
