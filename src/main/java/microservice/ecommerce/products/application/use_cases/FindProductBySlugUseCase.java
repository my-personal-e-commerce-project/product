package microservice.ecommerce.products.application.use_cases;

import microservice.ecommerce.products.application.ports.in.FindProductBySlugUseCasePort;
import microservice.ecommerce.products.domain.entity.Product;
import microservice.ecommerce.products.domain.exception.ProductNotFound;
import microservice.ecommerce.products.domain.repository.ProductRepository;

public class FindProductBySlugUseCase implements FindProductBySlugUseCasePort {

    private ProductRepository productRepository;

    public FindProductBySlugUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product execute(String slug) {
        Product product = productRepository.findBySlug(slug);

        if(product == null)
            throw new ProductNotFound();

        return product;
    }
}
