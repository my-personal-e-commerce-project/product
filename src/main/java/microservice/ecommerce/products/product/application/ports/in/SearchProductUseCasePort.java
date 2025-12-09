package microservice.ecommerce.products.product.application.ports.in;

import java.util.List;
import java.util.Map;

import microservice.ecommerce.products.product.domain.entity.Product;

public interface SearchProductUseCasePort {

    public List<Product> execute(String query, Map<String, String> sort, int page, int size);
}
