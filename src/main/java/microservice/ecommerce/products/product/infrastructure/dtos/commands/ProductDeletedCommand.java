package microservice.ecommerce.products.product.infrastructure.dtos.commands;

import java.time.Instant;

import microservice.ecommerce.products.product.infrastructure.dtos.EventConsume;

public record ProductDeletedCommand(
        String id,
        Instant occurredOn
) implements EventConsume {

    @Override
    public String aggregateId() {
        return id;
    }

    @Override
    public String eventName() {
        return "product.deleted";
    }

    public ProductDeletedCommand(
        String id
    ) {
        this(
            id,
            Instant.now()
        );
    } 
}
