package dev.mskory.repository.specification.impl.product;

import java.util.Optional;
import java.util.Set;
import dev.mskory.entity.Product;
import dev.mskory.repository.specification.SpecProvider;
import dev.mskory.repository.specification.SpecProviderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductSpecProviderManager implements SpecProviderManager<Product> {

    private final Set<SpecProvider<Product>> providers;

    @Override
    public Optional<SpecProvider<Product>> getProvider(String key) {
        return providers.stream()
                .filter(sp -> sp.getKey().equals(key))
                .findAny();
    }
}
