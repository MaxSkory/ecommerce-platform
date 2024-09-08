package dev.mskory.repository.specification.impl.product.provider;

import java.util.Arrays;
import dev.mskory.entity.Product;
import dev.mskory.repository.specification.SpecProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryIdSpecProvider implements SpecProvider<Product> {
    private static final String PARAM_NAME = "categoryIds";
    @Override
    public String getKey() {
        return PARAM_NAME;
    }

    @Override
    public Specification<Product> getSpecification(String[] params) {
        return ((root, query, criteriaBuilder) ->
                root.get("category").get("id").in(Arrays.asList(params)));
    }
}
