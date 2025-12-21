package microservice.ecommerce.products.category.infrastructure.dtos.events;

import microservice.ecommerce.products.product.infrastructure.dtos.EventConsume;

public record CategoryDeletedEvent(String id) implements EventConsume {

    @Override
    public String aggregateId() {
        return id;
    }
}
