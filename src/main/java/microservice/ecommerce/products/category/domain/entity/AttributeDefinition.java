package microservice.ecommerce.products.category.domain.entity;

public record AttributeDefinition(
    String id,
    String name,
    String slug,
    String type
) {}
