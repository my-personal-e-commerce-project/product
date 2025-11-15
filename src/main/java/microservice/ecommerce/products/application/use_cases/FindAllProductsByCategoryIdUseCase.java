package microservice.ecommerce.products.application.use_cases;

import java.util.List;
import java.util.Map;

import microservice.ecommerce.products.application.ports.in.FindAllProductsByCategoryIdUseCasePort;
import microservice.ecommerce.products.domain.entity.Product;
import microservice.ecommerce.products.domain.repository.ProductRepository;

public class FindAllProductsByCategoryIdUseCase implements FindAllProductsByCategoryIdUseCasePort {

    private ProductRepository productRepository;

    public FindAllProductsByCategoryIdUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> execute(String categoryId, Map<String, String> sort, int page, int size) {
        return productRepository.findAllByFilters(Map.of("category_id", categoryId), sort, page, size);
    }
}
