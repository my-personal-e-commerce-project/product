package microservice.ecommerce.products.application.ports.in;

import microservice.ecommerce.products.domain.entity.Product;

public interface FindProductByIdUseCasePort {

    public Product execute(String id);
}
