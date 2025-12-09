package microservice.ecommerce.products.product.infrastructure.mediator.handler;

import microservice.ecommerce.products.product.infrastructure.mediator.Handler;
import lombok.RequiredArgsConstructor;
import microservice.ecommerce.products.product.application.ports.in.CreateProductUseCasePort;
import microservice.ecommerce.products.product.infrastructure.dtos.ProductDto;
import microservice.ecommerce.products.product.infrastructure.helpers.MapProduct;

@RequiredArgsConstructor
public class CreatedProduct implements Handler<ProductDto> {

    private final CreateProductUseCasePort createProductUseCase;

    @Override
    public void handle(ProductDto event) {
        createProductUseCase.execute(
            MapProduct.map(event)
        );
    }
}
