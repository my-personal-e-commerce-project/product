package microservice.ecommerce.products.category.infrastructure.consumer;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import microservice.ecommerce.products.category.infrastructure.dtos.events.CategoryCreatedEvent;
import microservice.ecommerce.products.category.infrastructure.dtos.events.CategoryDeletedEvent;
import microservice.ecommerce.products.category.infrastructure.dtos.events.CategoryUpdatedEvent;
import microservice.ecommerce.products.category.infrastructure.helpers.MapCategory;
import microservice.ecommerce.products.category.application.ports.in.CreateCategoryUseCasePort;
import microservice.ecommerce.products.category.application.ports.in.DeleteCategoryUseCasePort;
import microservice.ecommerce.products.category.application.ports.in.UpdateCategoryUseCasePort;

@Configuration
@RequiredArgsConstructor
public class CategoryConsumers {

    private final CreateCategoryUseCasePort createCategoryUseCasePort;
    private final UpdateCategoryUseCasePort updateCategoryUseCasePort;
    private final DeleteCategoryUseCasePort deleteCategoryUseCasePort;

    @Bean
    public Consumer<CategoryCreatedEvent> createCategory() {
        return event -> {

            createCategoryUseCasePort.execute(MapCategory.fromProduct(event));
        };
    }

    @Bean
    public Consumer<CategoryUpdatedEvent> updateCategory() {
        return event -> {
            updateCategoryUseCasePort.execute(MapCategory.fromProduct(event));
        };
    }

    @Bean
    public Consumer<CategoryDeletedEvent> deleteCategory() {
        return event -> {
            deleteCategoryUseCasePort.execute(event.aggregateId());
        };
    }
}
