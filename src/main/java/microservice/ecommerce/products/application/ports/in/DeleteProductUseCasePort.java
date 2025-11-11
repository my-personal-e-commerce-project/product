package microservice.ecommerce.products.application.ports.in;

import microservice.ecommerce.products.domain.entity.Product;

public interface DeleteProductUseCasePort {

    public void execute(Product category);
}
