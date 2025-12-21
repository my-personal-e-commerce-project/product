package microservice.ecommerce.products.category.infrastructure.dtos;

public record CategoryAttributeDTO(
    AttributeDefinitionDTO attribute_definition,
    Boolean is_required,
    Boolean is_filterable,
    Boolean is_sortable
) {}
