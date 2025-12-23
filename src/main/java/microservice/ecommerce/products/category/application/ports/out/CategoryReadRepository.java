package microservice.ecommerce.products.category.application.ports.out;

import java.util.List;

import microservice.ecommerce.products.category.domain.entity.Category;
import microservice.ecommerce.products.shared.application.dtos.Pagination;

public interface CategoryReadRepository {
   
    public Pagination<Category> findAll(String parent_id, int page, int limit);
    public List<Category> getCategoriesByIds(List<String> ids);
}
