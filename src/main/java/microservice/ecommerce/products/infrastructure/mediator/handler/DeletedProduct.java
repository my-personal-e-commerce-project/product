package microservice.ecommerce.products.infrastructure.mediator.handler;

import lombok.RequiredArgsConstructor;
import microservice.ecommerce.products.application.ports.in.DeleteProductUseCasePort;
import microservice.ecommerce.products.infrastructure.dtos.ProductDto;
import microservice.ecommerce.products.infrastructure.helpers.MapProduct;
import microservice.ecommerce.products.infrastructure.mediator.Handler;

@RequiredArgsConstructor
public class DeletedProduct implements Handler<ProductDto> {
    
    private final DeleteProductUseCasePort deleteProductUseCase;

    @Override
    public void handle(ProductDto event) {
        deleteProductUseCase.execute(
            MapProduct.map(event)
        );
    }
}
