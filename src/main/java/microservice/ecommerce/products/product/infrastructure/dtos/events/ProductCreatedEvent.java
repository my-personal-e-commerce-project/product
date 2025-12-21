package microservice.ecommerce.products.product.infrastructure.dtos.events;

import java.util.List;

import microservice.ecommerce.products.product.domain.entity.ProductAttribute;
import microservice.ecommerce.products.product.infrastructure.dtos.EventConsume;

public record ProductCreatedEvent(
        String id,
        String title,
        String slug,
        String description,
        List<String> categories,
        double price,
        int stock,
        List<String> images,
        List<ProductAttribute> attributes,
        List<String> tags
) implements EventConsume {

    @Override
    public String aggregateId() {
        return id;
    }
}
