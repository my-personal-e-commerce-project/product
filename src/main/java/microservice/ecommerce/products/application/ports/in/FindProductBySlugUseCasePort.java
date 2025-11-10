package microservice.ecommerce.products.application.ports.in;

import microservice.ecommerce.products.domain.entity.Product;

public interface FindProductBySlugUseCasePort {

    public Product execute(String slug);
}
