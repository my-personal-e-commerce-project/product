package microservice.ecommerce.products.product.infrastructure.mediator.handler;

import lombok.RequiredArgsConstructor;
import microservice.ecommerce.products.product.application.ports.in.UpdateProductUseCasePort;
import microservice.ecommerce.products.product.infrastructure.dtos.ProductDto;
import microservice.ecommerce.products.product.infrastructure.helpers.MapProduct;
import microservice.ecommerce.products.product.infrastructure.mediator.Handler;

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
