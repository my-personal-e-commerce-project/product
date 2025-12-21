package microservice.ecommerce.products.category.infrastructure.persistence.repository.meilisearch;

import org.springframework.stereotype.Repository;

import io.vanslog.spring.data.meilisearch.repository.MeilisearchRepository;
import microservice.ecommerce.products.category.infrastructure.persistence.model.CategoryModel;

@Repository
public interface CategoryMeilisearchRepository extends MeilisearchRepository<CategoryModel, String> {

    
}
