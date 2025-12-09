package microservice.ecommerce.products.product.infrastructure.dtos.commands;

import java.time.Instant;
import java.util.List;

import microservice.ecommerce.products.product.infrastructure.dtos.EventConsume;

public record ProductUpdatedCommand(
        String id,
        String title,
        String slug,
        String description,
        List<String> categories,
        double price,
        int stock,
        List<String> images,
        List<ProductAttribute> attributes,
        Instant occurredOn
) implements EventConsume {

    @Override
    public String aggregateId() {
        return id;
    }

    @Override
    public String eventName() {
        return "product.updated";
    }

    public ProductUpdatedCommand(
        String id,
        String title,
        String slug,
        String description,
        List<String> categories,
        double price,
        int stock,
        List<ProductAttribute> attributes,
        List<String> images
    ) {
        this(
            id,
            title,
            slug,
            description,
            categories,
            price,
            stock,
            images,
            attributes,
            Instant.now()
        );
    } 
}
