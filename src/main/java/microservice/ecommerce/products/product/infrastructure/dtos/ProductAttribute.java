package microservice.ecommerce.products.product.infrastructure.dtos;

public record ProductAttribute (
    String attribute_definition,
    String string_value,
    Integer integer_value,
    Double double_value,
    Boolean boolean_value
) {}
