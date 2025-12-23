package microservice.ecommerce.products.category.domain.entity;

import microservice.ecommerce.products.category.domain.value_objects.DataType;

public record AttributeDefinition(
    String id,
    String name,
    String slug,
    DataType type
) {

    public void validTypeValue(
        String string_value, 
        Integer integer_value, 
        Double double_value, 
        Boolean boolean_value
    ) {
        int nonNullValues = 0;

        if (string_value != null) nonNullValues++;
        if (integer_value != null) nonNullValues++;
        if (double_value != null) nonNullValues++;
        if (boolean_value != null) nonNullValues++;

        if (nonNullValues != 1) {
            throw new RuntimeException(
                "Exactly one value must be provided for attribute " + this.id() 
            );
        }

        switch (this.type()) {
            case STRING -> {
                if (string_value == null)
                    throw new RuntimeException("string_value must not be null");
            }
            case INTEGER -> {
                if (integer_value == null)
                    throw new RuntimeException("integer_value must not be null");
            }
            case DOUBLE -> {
                if (double_value == null)
                    throw new RuntimeException("double_value must not be null");
            }
            case BOOLEAN -> {
                if (boolean_value == null)
                    throw new RuntimeException("boolean_value must not be null");
            }
            default -> throw new RuntimeException("Unsupported attribute type");
        }
    }
}
