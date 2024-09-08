package dev.mskory.repository.specification;

import java.util.Optional;
import dev.mskory.entity.Product;

public interface SpecProviderManager<T> {

    Optional<SpecProvider<Product>> getProvider(String key);
}
