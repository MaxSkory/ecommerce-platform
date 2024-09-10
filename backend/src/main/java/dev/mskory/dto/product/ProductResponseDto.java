package dev.mskory.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * DTO for {@link dev.mskory.entity.Product}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductResponseDto(
        Long id,
        String sku,
        String name,
        String description,
        BigDecimal unitPrice,
        String primaryImageUrl,
        List<String> imageUrls,
        boolean active,
        int unitsInStock,
        LocalDateTime dateCreated,
        LocalDateTime lastUpdate,
        Long categoryId
) implements Serializable {
}