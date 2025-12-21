package microservice.ecommerce.products.product.infrastructure.dtos.events;

import microservice.ecommerce.products.product.infrastructure.dtos.EventConsume;

public record ProductDeletedEvent(
        String id
) implements EventConsume {

    @Override
    public String aggregateId() {
        return id;
    }
}
