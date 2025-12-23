package microservice.ecommerce.products.product.application.dtos;

public record Sort(
    String attribute_definition_slug,
    String sortBy,
    String order
) {}
