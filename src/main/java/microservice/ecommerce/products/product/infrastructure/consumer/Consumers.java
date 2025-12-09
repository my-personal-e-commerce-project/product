package microservice.ecommerce.products.product.infrastructure.consumer;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import microservice.ecommerce.products.product.infrastructure.dtos.commands.ProductCreatedCommand;
import microservice.ecommerce.products.product.infrastructure.dtos.commands.ProductDeletedCommand;
import microservice.ecommerce.products.product.infrastructure.dtos.commands.ProductUpdatedCommand;
import microservice.ecommerce.products.product.infrastructure.mediator.Mediator;

@Configuration
@RequiredArgsConstructor
public class Consumers {

    private final Mediator mediator;

    @Bean
    public Consumer<ProductCreatedCommand> createProduct() {
        return event -> {
            mediator.send(event);
        };
    }

    @Bean
    public Consumer<ProductUpdatedCommand> updateProduct() {
        return event -> {
            mediator.send(event);
        };
    }

    @Bean
    public Consumer<ProductDeletedCommand> deleteProduct() {
        return event -> {
            mediator.send(event);
        };
    }
}
