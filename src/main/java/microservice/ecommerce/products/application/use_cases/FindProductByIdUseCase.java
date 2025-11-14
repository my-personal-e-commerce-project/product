package microservice.ecommerce.products.application.use_cases;

import microservice.ecommerce.products.application.ports.in.FindProductByIdUseCasePort;
import microservice.ecommerce.products.domain.entity.Product;
import microservice.ecommerce.products.domain.exception.ProductNotFound;
import microservice.ecommerce.products.domain.repository.ProductRepository;

public class FindProductByIdUseCase implements FindProductByIdUseCasePort {

    private ProductRepository productRepository;

    public FindProductByIdUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product execute(String id) {
        Product product = productRepository.findById(id);

        if(product == null)
            throw new ProductNotFound();

        return product;
    }
}
