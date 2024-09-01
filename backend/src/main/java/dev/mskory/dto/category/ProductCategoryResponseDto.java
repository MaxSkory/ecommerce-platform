package dev.mskory.dto.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

/**
 * DTO for {@link dev.mskory.entity.ProductCategory}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductCategoryResponseDto(
        Long id,
        String name
) implements Serializable {
}