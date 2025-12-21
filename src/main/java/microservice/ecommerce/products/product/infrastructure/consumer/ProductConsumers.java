package microservice.ecommerce.products.product.infrastructure.consumer;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import microservice.ecommerce.products.product.application.ports.in.CreateProductUseCasePort;
import microservice.ecommerce.products.product.application.ports.in.DeleteProductUseCasePort;
import microservice.ecommerce.products.product.application.ports.in.UpdateProductUseCasePort;
import microservice.ecommerce.products.product.infrastructure.dtos.events.ProductCreatedEvent;
import microservice.ecommerce.products.product.infrastructure.dtos.events.ProductDeletedEvent;
import microservice.ecommerce.products.product.infrastructure.dtos.events.ProductUpdatedEvent;
import microservice.ecommerce.products.product.infrastructure.helpers.MapProduct;

@Configuration
@RequiredArgsConstructor
public class ProductConsumers {

    private final CreateProductUseCasePort createProductUseCasePort;
    private final UpdateProductUseCasePort updateProductUseCasePort;
    private final DeleteProductUseCasePort deleteProductUseCasePort;

    @Bean
    public Consumer<ProductCreatedEvent> createProduct() {
        return event -> {

            createProductUseCasePort.execute(MapProduct.fromProduct(event));
        };
    }

    @Bean
    public Consumer<ProductUpdatedEvent> updateProduct() {
        return event -> {
            System.out.println("hola");
            System.out.println("hola");
            System.out.println("hola");
            System.out.println(event);
            updateProductUseCasePort.execute(MapProduct.fromProduct(event));
        };
    }

    @Bean
    public Consumer<ProductDeletedEvent> deleteProduct() {
        return event -> {
            deleteProductUseCasePort.execute(event.aggregateId());
        };
    }
}
