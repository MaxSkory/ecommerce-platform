package dev.mskory.dto.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * DTO for {@link dev.mskory.entity.ProductCategory}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductCategoryRequestDto(
        @NotBlank
        String name
) implements Serializable {
}