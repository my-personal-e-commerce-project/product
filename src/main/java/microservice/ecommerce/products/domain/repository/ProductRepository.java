package microservice.ecommerce.products.domain.repository;

import microservice.ecommerce.products.domain.entity.Product;

public interface ProductRepository {
    public Product findById(String id);
    public Product findBySlug(String slug);
    public void save(Product product);
    public void update(Product product);
    public void delete(String id);
}
