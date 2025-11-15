package microservice.ecommerce.products.application.ports.in;

import java.util.List;
import java.util.Map;

import microservice.ecommerce.products.domain.entity.Product;

public interface FindAllProductsByCategoryIdUseCasePort {

    public List<Product> execute(String categoryId, Map<String, String> sort, int page, int size);
}
