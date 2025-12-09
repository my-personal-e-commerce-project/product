package microservice.ecommerce.products.product.application.ports.in;

import microservice.ecommerce.products.product.domain.entity.Product;

public interface FindProductByIdUseCasePort {

    public Product execute(String id);
}
