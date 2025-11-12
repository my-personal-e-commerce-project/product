package microservice.ecommerce.products.infrastructure.mediator.handler;

import lombok.RequiredArgsConstructor;
import microservice.ecommerce.products.application.ports.in.CreateCategoryUseCasePort;
import microservice.ecommerce.products.infrastructure.dtos.CategoryDto;
import microservice.ecommerce.products.infrastructure.helpers.MapCategory;
import microservice.ecommerce.products.infrastructure.mediator.Handler;

@RequiredArgsConstructor
public class CreatedCategory implements Handler<CategoryDto>{

    private final CreateCategoryUseCasePort createCategoryUseCase;

    @Override
    public void handle(CategoryDto event) {
        createCategoryUseCase.execute(
            MapCategory.map(event)
        );
    }
}
