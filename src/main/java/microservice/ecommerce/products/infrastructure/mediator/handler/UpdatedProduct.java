package microservice.ecommerce.products.infrastructure.mediator.handler;

import microservice.ecommerce.products.infrastructure.dtos.ProductDto;
import microservice.ecommerce.products.infrastructure.mediator.Handler;

public class UpdatedProduct implements Handler<ProductDto> {

    @Override
    public void handle(ProductDto eventConsume) {
        System.out.println("Product updated");
    }
}
