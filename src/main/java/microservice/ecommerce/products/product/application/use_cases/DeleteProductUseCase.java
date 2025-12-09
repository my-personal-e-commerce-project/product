package microservice.ecommerce.products.product.application.use_cases;

import microservice.ecommerce.products.product.application.ports.in.DeleteProductUseCasePort;
import microservice.ecommerce.products.product.domain.entity.Product;
import microservice.ecommerce.products.product.domain.repository.ProductRepository;

public class DeleteProductUseCase implements DeleteProductUseCasePort {

    private ProductRepository productRepository;

    public DeleteProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void execute(Product product) {
        productRepository.delete(product.id());
    }
}
