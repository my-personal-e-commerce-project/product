package microservice.ecommerce.products.product.infrastructure.helpers;

import microservice.ecommerce.products.product.domain.entity.Product;
import microservice.ecommerce.products.product.infrastructure.dtos.events.ProductUpdatedEvent;
import microservice.ecommerce.products.product.infrastructure.dtos.events.ProductCreatedEvent;

public class MapProduct {

    public static Product fromProduct(ProductCreatedEvent product) {
        return new Product(
            product.id(),
            product.title(),
            product.slug(),
            product.description(),
            product.categories(),
            product.price(),
            product.stock(),
            product.images(),
            product.attributes(),
            product.tags()
        );
    }

    public static Product fromProduct(ProductUpdatedEvent product) {
        return new Product(
            product.id(),
            product.title(),
            product.slug(),
            product.description(),
            product.categories(),
            product.price(),
            product.stock(),
            product.images(),
            product.attributes(),
            product.tags()
        );
    }
}
