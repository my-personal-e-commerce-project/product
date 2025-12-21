package microservice.ecommerce.products.category.domain.repository;

import java.util.List;

import microservice.ecommerce.products.category.domain.entity.Category;
import microservice.ecommerce.products.category.domain.entity.CategoryAttribute;
import microservice.ecommerce.products.shared.application.dtos.Pagination;

public interface CategoryRepository {

    public void save(Category category);
    public void update(Category category);
    public void delete(String id);
}
