package microservice.ecommerce.products.infrastructure.repository.mongodb;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import microservice.ecommerce.products.infrastructure.model.CategoryModel;

public interface CategoryMongoRepository extends MongoRepository<CategoryModel, String> {
    public Optional<CategoryModel> findBySlug(String slug);
}
