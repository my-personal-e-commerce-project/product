package microservice.ecommerce.products.product.infrastructure.presentation.dtos;

import java.util.List;

import microservice.ecommerce.products.product.application.dtos.Filter;

public record Filters (
    List<Filter> filters
) {}
