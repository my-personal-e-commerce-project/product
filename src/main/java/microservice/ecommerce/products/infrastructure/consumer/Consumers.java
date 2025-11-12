package microservice.ecommerce.products.infrastructure.consumer;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import microservice.ecommerce.products.infrastructure.dtos.CategoryDto;
import microservice.ecommerce.products.infrastructure.dtos.DeleteDto;
import microservice.ecommerce.products.infrastructure.dtos.EventConsume;
import microservice.ecommerce.products.infrastructure.dtos.ProductDto;
import microservice.ecommerce.products.infrastructure.mediator.Mediator;

@Configuration
@RequiredArgsConstructor
public class Consumers {

    private final Mediator mediator;

    @Bean
    public Consumer<EventConsume<ProductDto>> productConsumer() {
        return event -> {
            mediator.send(event);
        };
    }

    @Bean
    public Consumer<EventConsume<DeleteDto>> productDeletedConsumer() {
        return event -> {
            mediator.send(event);
        };
    }

    @Bean
    public Consumer<EventConsume<CategoryDto>> categoryConsumer() {
        return event -> {
            mediator.send(event);
        };
    }
}
