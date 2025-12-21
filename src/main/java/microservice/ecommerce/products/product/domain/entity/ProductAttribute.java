package microservice.ecommerce.products.product.domain.entity;

public record ProductAttribute (
    String attribute_definition,
    String string_value,
    Integer integer_value,
    Double double_value,
    Boolean boolean_value
) {}
