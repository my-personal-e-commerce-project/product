package microservice.ecommerce.products.product.application.dtos;

public record Filter (
    String attribute_definition_slug,
    String string_value,
    Boolean boolean_value,
    Double double_value,
    Integer integer_value 
) {}
