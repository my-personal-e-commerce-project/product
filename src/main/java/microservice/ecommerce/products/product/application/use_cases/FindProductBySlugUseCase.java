package microservice.ecommerce.products.product.application.use_cases;

import microservice.ecommerce.products.product.application.ports.in.FindProductBySlugUseCasePort;
import microservice.ecommerce.products.product.domain.entity.Product;
import microservice.ecommerce.products.product.domain.exception.ProductNotFound;
import microservice.ecommerce.products.product.domain.repository.ProductRepository;

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
