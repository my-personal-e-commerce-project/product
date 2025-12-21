package microservice.ecommerce.products.product.domain.entity;

import java.util.List;

public record Product(
    String id,
    String title,
    String slug,
    String description,
    List<String> categories,
    double price,
    int stock,
    List<String> images,
    List<ProductAttribute> attributes,
    List<String> tags
) {}
