package dev.mskory.controller;

import jakarta.validation.Valid;
import java.util.List;
import dev.mskory.dto.category.ProductCategoryRequestDto;
import dev.mskory.dto.category.ProductCategoryResponseDto;
import dev.mskory.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/categories"))
@RequiredArgsConstructor
public class ProductCategoryController {

    private final ProductCategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductCategoryResponseDto create(@RequestBody @Valid ProductCategoryRequestDto dto) {
        return categoryService.create(dto);
    }

    @GetMapping("/{id}")
    public ProductCategoryResponseDto getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @GetMapping
    public List<ProductCategoryResponseDto> getPage() {
        return categoryService.getAll();
    }

    @PatchMapping("/{id}")
    public ProductCategoryResponseDto update(@PathVariable Long id, @RequestBody ProductCategoryRequestDto dto) {
        return categoryService.updateById(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
