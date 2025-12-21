package microservice.ecommerce.products.category.infrastructure.helpers;

import microservice.ecommerce.products.category.domain.entity.Category;
import microservice.ecommerce.products.category.infrastructure.dtos.events.CategoryCreatedEvent;
import microservice.ecommerce.products.category.infrastructure.dtos.events.CategoryUpdatedEvent;

public class MapCategory {

    public static Category fromProduct(CategoryCreatedEvent product) {
        return new Category(
            product.id(),
            product.name(),
            product.slug(),
            product.parent_id(),
            product.attributes()
        );
    }

    public static Category fromProduct(CategoryUpdatedEvent product) {
        return new Category(
            product.id(),
            product.name(),
            product.slug(),
            product.parent_id(),
            product.attributes()
        );    
    }
}
