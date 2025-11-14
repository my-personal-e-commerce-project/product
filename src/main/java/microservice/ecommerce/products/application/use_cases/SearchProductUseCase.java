package microservice.ecommerce.products.application.use_cases;

import java.util.List;

import microservice.ecommerce.products.application.ports.in.SearchProductUseCasePort;
import microservice.ecommerce.products.domain.entity.Product;
import microservice.ecommerce.products.domain.repository.ProductRepository;

public class SearchProductUseCase implements SearchProductUseCasePort {

    private ProductRepository productRepository;

    public SearchProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> execute(String query, int page, int size) {
        return productRepository.search(query, page, size);
    }
}
