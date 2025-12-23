package microservice.ecommerce.products.product.application.use_cases;

import microservice.ecommerce.products.product.application.ports.in.FindProductBySlugUseCasePort;
import microservice.ecommerce.products.product.application.ports.out.ProductReadRepository;
import microservice.ecommerce.products.product.domain.entity.Product;
import microservice.ecommerce.products.product.domain.exception.ProductNotFound;

public class FindProductBySlugUseCase implements FindProductBySlugUseCasePort {

    private ProductReadRepository productRepository;

    public FindProductBySlugUseCase(ProductReadRepository productRepository) {
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
