package microservice.ecommerce.products.application.ports.in;

import microservice.ecommerce.products.domain.entity.Product;

public interface UpdateProductUseCasePort {

    public void execute(Product product);
}
