package microservice.ecommerce.products.infrastructure.mediator.handler;

import microservice.ecommerce.products.infrastructure.mediator.Handler;
import microservice.ecommerce.products.infrastructure.dtos.ProductDto;

public class CreatedProduct implements Handler<ProductDto> {

    @Override
    public void handle(ProductDto eventConsume) {
        System.out.println("Product created");
    }
}
