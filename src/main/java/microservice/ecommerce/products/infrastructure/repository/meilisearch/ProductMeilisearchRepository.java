package microservice.ecommerce.products.infrastructure.repository.meilisearch;

import org.springframework.stereotype.Repository;

import io.vanslog.spring.data.meilisearch.repository.MeilisearchRepository;
import microservice.ecommerce.products.infrastructure.model.ProductModel;

@Repository
public interface ProductMeilisearchRepository extends MeilisearchRepository<ProductModel, String> {
    
}
