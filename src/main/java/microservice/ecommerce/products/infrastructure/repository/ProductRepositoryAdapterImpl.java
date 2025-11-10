package microservice.ecommerce.products.infrastructure.repository;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import microservice.ecommerce.products.domain.entity.Product;
import microservice.ecommerce.products.domain.repository.ProductRepository;
import microservice.ecommerce.products.infrastructure.model.ProductModel;
import microservice.ecommerce.products.infrastructure.repository.mongodb.ProductMongoRepository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryAdapterImpl implements ProductRepository {

    private final ProductMongoRepository productMongoRepository; 

    @Override
    public Product findById(String id) {
        ProductModel product = productMongoRepository.findById(id).orElse(null);

        if(product == null)
            return null;

        return toDomain(product);
    }

    @Override
    public Product findBySlug(String slug) {
        ProductModel product = productMongoRepository.findBySlug(slug).orElse(null);

        if(product == null)
            return null;

        return toDomain(product);
    }

    @Override
    public void save(Product product) {
        ProductModel productModel = toDocument(product);

        productMongoRepository.save(productModel);
    }

    @Override
    public void update(Product product) {
        ProductModel productModel = productMongoRepository.findById(product.id()).orElse(null);

        if(productModel == null)
            return;

        productModel.setName(product.name());
        productModel.setSlug(product.slug());
        productModel.setDescription(product.description());
        productModel.setPrice(product.price());
        productModel.setStock(product.stock());
        productModel.setImages(product.images());
        productModel.setCategory_id(product.category_id());

        productMongoRepository.save(productModel);
    }

    @Override
    public void delete(String id) {
        ProductModel productModel = productMongoRepository.findById(id).orElse(null);

        if(productModel == null)
            return;

        productMongoRepository.deleteById(id);
    }

    private ProductModel toDocument(Product product) {
        return new ProductModel(
            product.id(),
            product.name(),
            product.slug(),
            product.description(),
            product.price(),
            product.stock(),
            product.images(),
            product.category_id()
        ); 
    }

    private Product toDomain(ProductModel productModel) {
        return new Product(
            productModel.getId(),
            productModel.getName(),
            productModel.getSlug(),
            productModel.getDescription(),
            productModel.getPrice(),
            productModel.getStock(),
            productModel.getImages(),
            productModel.getCategory_id()
        );
    }
}
