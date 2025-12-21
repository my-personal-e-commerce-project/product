package microservice.ecommerce.products.product.application.dtos;

import java.util.List;

import microservice.ecommerce.products.product.domain.entity.Product;

public record PaginationProducts(
    List<Product> result,
    List<String> categoryIds,
    int current_page,
    int last_page
) {}
