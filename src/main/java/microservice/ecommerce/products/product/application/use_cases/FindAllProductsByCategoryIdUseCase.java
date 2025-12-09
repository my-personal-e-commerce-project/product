package microservice.ecommerce.products.product.application.use_cases;

import java.util.List;
import java.util.Map;

import microservice.ecommerce.products.product.application.ports.in.FindAllProductsByCategoryIdUseCasePort;
import microservice.ecommerce.products.product.domain.entity.Product;
import microservice.ecommerce.products.product.domain.repository.ProductRepository;

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
