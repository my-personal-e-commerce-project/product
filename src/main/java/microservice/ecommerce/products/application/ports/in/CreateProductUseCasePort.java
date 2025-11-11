package microservice.ecommerce.products.application.ports.in;

import microservice.ecommerce.products.domain.entity.Product;

public interface CreateProductUseCasePort {

    public void execute(Product product);
}
