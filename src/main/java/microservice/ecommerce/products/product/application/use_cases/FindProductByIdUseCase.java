package microservice.ecommerce.products.product.application.use_cases;

import microservice.ecommerce.products.product.application.ports.in.FindProductByIdUseCasePort;
import microservice.ecommerce.products.product.application.ports.out.ProductReadRepository;
import microservice.ecommerce.products.product.domain.entity.Product;
import microservice.ecommerce.products.product.domain.exception.ProductNotFound;

public class FindProductByIdUseCase implements FindProductByIdUseCasePort {

    private ProductReadRepository productRepository;

    public FindProductByIdUseCase(ProductReadRepository productRepository) {
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
