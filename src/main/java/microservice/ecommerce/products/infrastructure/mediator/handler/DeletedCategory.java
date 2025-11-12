package microservice.ecommerce.products.infrastructure.mediator.handler;

import lombok.RequiredArgsConstructor;
import microservice.ecommerce.products.application.ports.in.DeleteCategoryUseCasePort;
import microservice.ecommerce.products.infrastructure.dtos.CategoryDto;
import microservice.ecommerce.products.infrastructure.dtos.DeleteDto;
import microservice.ecommerce.products.infrastructure.helpers.MapCategory;
import microservice.ecommerce.products.infrastructure.mediator.Handler;

@RequiredArgsConstructor
public class DeletedCategory implements Handler<DeleteDto> {
    
    private final DeleteCategoryUseCasePort deleteCategoryUseCase;

    @Override
    public void handle(DeleteDto event) {
        CategoryDto category = CategoryDto.builder().id(event.getId()).build();
        deleteCategoryUseCase.execute(MapCategory.map(category));
    }
}
