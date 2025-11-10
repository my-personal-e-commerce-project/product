package microservice.ecommerce.products.infrastructure.repository.mongodb;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import microservice.ecommerce.products.infrastructure.model.ProductModel;

public interface ProductMongoRepository extends MongoRepository<ProductModel, String> {
    public Optional<ProductModel> findBySlug(String slug);
}
