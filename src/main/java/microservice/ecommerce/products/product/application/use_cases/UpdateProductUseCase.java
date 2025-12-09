package microservice.ecommerce.products.product.application.use_cases;

import microservice.ecommerce.products.product.application.ports.in.UpdateProductUseCasePort;
import microservice.ecommerce.products.product.domain.entity.Product;
import microservice.ecommerce.products.product.domain.repository.ProductRepository;

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
