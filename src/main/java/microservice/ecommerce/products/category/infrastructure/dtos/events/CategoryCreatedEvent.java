package microservice.ecommerce.products.category.infrastructure.dtos.events;

import java.util.List;

import microservice.ecommerce.products.category.domain.entity.CategoryAttribute;
import microservice.ecommerce.products.product.infrastructure.dtos.EventConsume;

public record CategoryCreatedEvent(
    String id,
    String name,
    String slug,
    String parent_id,
    List<CategoryAttribute> attributes
) implements EventConsume {

    @Override
    public String aggregateId() {
        return id;
    }
}
