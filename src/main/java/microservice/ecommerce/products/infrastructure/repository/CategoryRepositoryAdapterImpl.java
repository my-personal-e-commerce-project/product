package microservice.ecommerce.products.infrastructure.repository;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import microservice.ecommerce.products.domain.entity.Category;
import microservice.ecommerce.products.domain.repository.CategoryRepository;
import microservice.ecommerce.products.infrastructure.model.CategoryModel;
import microservice.ecommerce.products.infrastructure.repository.mongodb.CategoryMongoRepository;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryAdapterImpl implements CategoryRepository {

    private final CategoryMongoRepository categoryMongoRepository;

    @Override
    public Category findBySlug(String slug) {
        CategoryModel category = categoryMongoRepository.findBySlug(slug).orElse(null);

        if(category != null)
            return toDomain(category);

        return null;
    }

    @Override
    public Category findById(String id) {
        CategoryModel category = categoryMongoRepository.findById(id).orElse(null);

        if(category != null)
            return toDomain(category);

        return null;
    }

    @Override
    public void save(Category product) {
        categoryMongoRepository.save(toDocument(product));
    }

    @Override
    public void update(Category product) {
        CategoryModel category = categoryMongoRepository.findById(product.id()).orElse(null);

        if(category != null){
            category.setName(product.name());
            category.setSlug(product.slug());
            categoryMongoRepository.save(toDocument(product));
        }
    }

    @Override
    public void delete(String id) {
        categoryMongoRepository.deleteById(id);
    }

    private Category toDomain(CategoryModel categoryModel) {
        return new Category(
            categoryModel.getId(),
            categoryModel.getName(),
            categoryModel.getSlug()
        );
    }

    private CategoryModel toDocument(Category category) {
        return new CategoryModel(
            category.id(),
            category.name(),
            category.slug()
        );
    }
}
