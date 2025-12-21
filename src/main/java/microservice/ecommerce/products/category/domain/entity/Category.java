package microservice.ecommerce.products.category.domain.entity;

import java.util.List;

public record Category(
    String id,
    String name,
    String slug,
    String parent_id,
    List<CategoryAttribute> attributes
) {}
