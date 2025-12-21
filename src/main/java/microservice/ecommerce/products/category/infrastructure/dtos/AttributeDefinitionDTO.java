package microservice.ecommerce.products.category.infrastructure.dtos;

public record AttributeDefinitionDTO(
    String id,
    String name,
    String slug,
    String type
) {}
