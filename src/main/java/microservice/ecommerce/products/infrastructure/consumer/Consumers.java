package microservice.ecommerce.products.infrastructure.consumer;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import microservice.ecommerce.products.infrastructure.dtos.DeleteDto;
import microservice.ecommerce.products.infrastructure.dtos.EventConsume;
import microservice.ecommerce.products.infrastructure.dtos.ProductDto;
import microservice.ecommerce.products.infrastructure.mediator.Mediator;

@Configuration
@RequiredArgsConstructor
public class Consumers {

    private final Mediator mediator;

    @Bean
    public Consumer<EventConsume> productConsumer() {
        return event -> {
            String type = event.getEvent();
            ObjectMapper mapper = new ObjectMapper();

            if (type.endsWith("product.deleted")) {
                DeleteDto delete = mapper.convertValue(event.getPayload(), DeleteDto.class);
                mediator.send(type, delete);
            } else if (type.startsWith("product")) {
                ProductDto product = mapper.convertValue(event.getPayload(), ProductDto.class);
                mediator.send(type, product);
            }
        };
    }
}
