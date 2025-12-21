package microservice.ecommerce.products.shared.application.dtos;

import java.util.List;

public record Pagination<T> (
    List<T> result,
    String current_page,
    String last_page
) {}
