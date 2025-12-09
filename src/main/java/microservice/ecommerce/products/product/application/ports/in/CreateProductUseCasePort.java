package microservice.ecommerce.products.product.application.ports.in;

import microservice.ecommerce.products.product.domain.entity.Product;

public interface CreateProductUseCasePort {

    public void execute(Product product);
}
