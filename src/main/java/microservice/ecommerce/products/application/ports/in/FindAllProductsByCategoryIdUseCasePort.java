package microservice.ecommerce.products.application.ports.in;

import java.util.List;

import microservice.ecommerce.products.domain.entity.Product;

public interface FindAllProductsByCategoryIdUseCasePort {

    public List<Product> execute(String categoryId, int page, int size);
}
