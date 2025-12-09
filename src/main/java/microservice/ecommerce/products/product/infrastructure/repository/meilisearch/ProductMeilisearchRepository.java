package microservice.ecommerce.products.product.infrastructure.repository.meilisearch;

import org.springframework.stereotype.Repository;

import io.vanslog.spring.data.meilisearch.repository.MeilisearchRepository;
import microservice.ecommerce.products.product.infrastructure.model.ProductModel;

@Repository
public interface ProductMeilisearchRepository extends MeilisearchRepository<ProductModel, String> {
    
}
