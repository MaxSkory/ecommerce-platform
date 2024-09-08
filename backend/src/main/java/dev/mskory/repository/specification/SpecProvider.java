package dev.mskory.repository.specification;

import org.springframework.data.jpa.domain.Specification;

public interface SpecProvider<T> {

    String getKey();

    Specification<T> getSpecification(String[] params);
}
