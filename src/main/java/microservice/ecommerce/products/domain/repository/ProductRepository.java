package microservice.ecommerce.products.domain.repository;

import java.util.List;

import microservice.ecommerce.products.domain.entity.Product;

public interface ProductRepository {
    public List<Product> search(String query, int page, int size);
    public List<Product> findAllByCategory(String categoryId, int page, int size);
    public Product findById(String id);
    public Product findBySlug(String slug);
    public void save(Product product);
    public void update(Product product);
    public void delete(String id);
}
