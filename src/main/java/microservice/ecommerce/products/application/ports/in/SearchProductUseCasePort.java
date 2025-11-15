package microservice.ecommerce.products.application.ports.in;

import java.util.List;
import java.util.Map;

import microservice.ecommerce.products.domain.entity.Product;

public interface SearchProductUseCasePort {

    public List<Product> execute(String query, Map<String, String> sort, int page, int size);
}
