package dev.mskory.dto.product;

public record ProductSpecificationDto(
        String[] categoryIds,
        String[] keywords,
        String[] priceRange
) {
}
