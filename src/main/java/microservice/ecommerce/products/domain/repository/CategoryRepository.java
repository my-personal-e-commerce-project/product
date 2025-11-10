package microservice.ecommerce.products.domain.repository;

import microservice.ecommerce.products.domain.entity.Category;

public interface CategoryRepository {
    public Category findById(String id);
    public Category findBySlug(String slug);
    public void save(Category product);
    public void update(Category product);
    public void delete(String id);
}
