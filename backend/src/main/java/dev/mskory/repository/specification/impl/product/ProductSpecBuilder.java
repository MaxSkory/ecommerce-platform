package dev.mskory.repository.specification.impl.product;

import java.lang.reflect.Field;
import java.util.Optional;
import dev.mskory.entity.Product;
import dev.mskory.exception.DataProcessingException;
import dev.mskory.repository.specification.SpecBuilder;
import dev.mskory.repository.specification.SpecProvider;
import dev.mskory.repository.specification.SpecProviderManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductSpecBuilder implements SpecBuilder<Product> {

    private final SpecProviderManager<Product> providerManager;

    @Override
    public Specification<Product> build(Record searchParams) {
        Specification<Product> spec = Specification.where(null);
        Field[] fields = searchParams.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Optional<SpecProvider<Product>> provider = providerManager.getProvider(field.getName());
            if (provider.isEmpty()) {
                log.warn("Can't find Product specification provider by key: " + field.getName());
                continue;
            }
            try {
                String[] specParams = (String[]) field.get(searchParams);
                if (specParams != null) {
                    spec = spec.and(provider.get().getSpecification(specParams));
                }
            } catch (IllegalAccessException e) {
                throw new DataProcessingException("Can't build specification "
                        + "for search parameters " + searchParams, e);
            }
        }
        return spec;
    }
}
