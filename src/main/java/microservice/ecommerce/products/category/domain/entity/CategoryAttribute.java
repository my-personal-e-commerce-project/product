package microservice.ecommerce.products.category.domain.entity;

public record CategoryAttribute(
    AttributeDefinition attribute_definition,
    Boolean is_required,
    Boolean is_filterable,
    Boolean is_sortable
) {}
