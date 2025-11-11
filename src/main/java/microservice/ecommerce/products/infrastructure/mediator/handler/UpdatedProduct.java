package microservice.ecommerce.products.infrastructure.mediator.handler;

import lombok.RequiredArgsConstructor;
import microservice.ecommerce.products.application.ports.in.UpdateProductUseCasePort;
import microservice.ecommerce.products.infrastructure.dtos.ProductDto;
import microservice.ecommerce.products.infrastructure.helpers.MapProduct;
import microservice.ecommerce.products.infrastructure.mediator.Handler;

@RequiredArgsConstructor
public class UpdatedProduct implements Handler<ProductDto> {

    private final UpdateProductUseCasePort updateProductUseCase;

    @Override
    public void handle(ProductDto event) {
        updateProductUseCase.execute(
            MapProduct.map(event)
        );
    }
}
