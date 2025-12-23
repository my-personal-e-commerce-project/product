package microservice.ecommerce.products.category.application.ports.in;

import microservice.ecommerce.products.category.domain.entity.Category;
import microservice.ecommerce.products.shared.application.dtos.Pagination;

public interface ListCategoriesUseCasePort {
    public Pagination<Category> execute(String parent_id, int page, int limit);
}
