package microservice.ecommerce.products.application.use_cases;

import microservice.ecommerce.products.application.ports.in.CreateProductUseCasePort;
import microservice.ecommerce.products.domain.entity.Product;
import microservice.ecommerce.products.domain.repository.ProductRepository;

public class CreateProductUseCase implements CreateProductUseCasePort {
    
    private ProductRepository productRepository;

    public CreateProductUseCase(
        ProductRepository productRepository
    ) {
        this.productRepository = productRepository;
    }

    @Override
    public void execute(Product product) {
        productRepository.save(product);
    }
}
