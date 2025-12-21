package microservice.ecommerce.products.product.application.use_cases;

import java.util.List;
import java.util.Map;

import microservice.ecommerce.products.product.application.dtos.PaginationProducts;
import microservice.ecommerce.products.product.application.dtos.Sort;
import microservice.ecommerce.products.product.application.ports.in.SearchProductUseCasePort;
import microservice.ecommerce.products.product.domain.entity.Product;
import microservice.ecommerce.products.product.domain.repository.ProductRepository;

public class SearchProductUseCase implements SearchProductUseCasePort {

    private ProductRepository productRepository;

    public SearchProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public PaginationProducts execute(String query, Sort sort, Map<String, String> filters, int page, int size) {
        return productRepository.search(query, sort, filters, page, size);
    }
}
