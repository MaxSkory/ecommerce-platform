package dev.mskory.repository.specification.impl.product.provider;

import jakarta.persistence.criteria.Predicate;
import dev.mskory.entity.Product;
import dev.mskory.repository.specification.SpecProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductNameSpecProvider implements SpecProvider<Product> {
    @Override
    public String getKey() {
        return "keywords";
    }

    @Override
    public Specification<Product> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> {
            Predicate combinedPredicate = criteriaBuilder.disjunction();
            for (String param : params) {
                String pattern = "%" + param.toLowerCase() + "%";
                combinedPredicate = criteriaBuilder.or(combinedPredicate, criteriaBuilder
                        .like(criteriaBuilder.lower(root.get("name")), pattern));
            }
            return combinedPredicate;
        };
    }
}
