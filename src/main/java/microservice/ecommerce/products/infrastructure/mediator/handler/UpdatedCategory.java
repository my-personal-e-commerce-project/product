package microservice.ecommerce.products.infrastructure.mediator.handler;

import lombok.RequiredArgsConstructor;
import microservice.ecommerce.products.application.ports.in.UpdateCategoryUseCasePort;
import microservice.ecommerce.products.infrastructure.dtos.CategoryDto;
import microservice.ecommerce.products.infrastructure.helpers.MapCategory;
import microservice.ecommerce.products.infrastructure.mediator.Handler;

@RequiredArgsConstructor
public class UpdatedCategory implements Handler<CategoryDto> {

    private final UpdateCategoryUseCasePort updateCategoryUseCase;

    @Override
    public void handle(CategoryDto event) {
        updateCategoryUseCase.execute(MapCategory.map(event)); 
    }
}
