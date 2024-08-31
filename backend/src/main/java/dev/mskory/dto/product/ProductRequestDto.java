package dev.mskory.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link dev.mskory.entity.Product}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductRequestDto(
        @NotBlank
        String sku,
        @NotBlank
        String name,
        String description,
        @Digits(integer = 13, fraction = 2)
        BigDecimal unitPrice,
        @NotBlank
        String imageUrl,
        @NotNull
        int unitsInStock,
        @NotNull
        boolean active,
        @NotNull
        Long categoryId
) implements Serializable {
}