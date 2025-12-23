package microservice.ecommerce.products.shared.application.dtos;

import java.util.List;

public record Pagination<T> (
    List<T> result,
    int current_page,
    int last_page
) {}
