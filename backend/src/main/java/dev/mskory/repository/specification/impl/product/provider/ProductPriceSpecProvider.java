package dev.mskory.repository.specification.impl.product.provider;

import dev.mskory.entity.Product;
import dev.mskory.repository.specification.SpecProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductPriceSpecProvider implements SpecProvider<Product> {
    private static final String PARAM_NAME = "priceRange";
    @Override
    public String getKey() {
        return PARAM_NAME;
    }

    @Override
    public Specification<Product> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("unitPrice"), params[0], params[1]);
    }
}
