package microservice.ecommerce.products.application.ports.in;

import java.util.List;

import microservice.ecommerce.products.domain.entity.Product;

public interface SearchProductUseCasePort {

    public List<Product> execute(String query, int page, int size);
}
