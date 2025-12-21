package microservice.ecommerce.products.category.infrastructure.persistence.repository;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import microservice.ecommerce.products.category.domain.entity.Category;
import microservice.ecommerce.products.category.domain.repository.CategoryRepository;
import microservice.ecommerce.products.category.infrastructure.persistence.model.CategoryModel;
import microservice.ecommerce.products.category.infrastructure.persistence.repository.meilisearch.CategoryMeilisearchRepository;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryJpaAdapter implements CategoryRepository {

    private final CategoryMeilisearchRepository categoryMeilisearchRepository;

    @Override
    public void save(Category category) {
        categoryMeilisearchRepository.save(toModel(category));
    }

    @Override
    public void update(Category category) {
        categoryMeilisearchRepository.save(toModel(category));
    }

    @Override
    public void delete(String id) {
        categoryMeilisearchRepository.deleteById(id);
    }

    private CategoryModel toModel(Category category) {
        return new CategoryModel(
            category.id(), 
            category.name(),
            category.slug(), 
            category.parent_id(), 
            category.attributes()
        );
    }
}
