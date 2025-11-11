package microservice.ecommerce.products.application.use_cases;

import microservice.ecommerce.products.application.ports.in.UpdateProductUseCasePort;
import microservice.ecommerce.products.domain.entity.Product;
import microservice.ecommerce.products.domain.repository.ProductRepository;

public class UpdateProductUseCase implements UpdateProductUseCasePort {

    private ProductRepository productRepository;

    public UpdateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void execute(Product product) {
        productRepository.update(product); 
    }
}
