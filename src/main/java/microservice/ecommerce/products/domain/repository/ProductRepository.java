package microservice.ecommerce.products.domain.repository;

import java.util.List;
import java.util.Map;

import microservice.ecommerce.products.domain.entity.Product;

public interface ProductRepository {
    public List<Product> search(String query, Map<String, String> sort, int page, int size);
    public List<Product> findAllByFilters(Map<String, String> filters, Map<String, String> sort, int page, int size);
    public Product findById(String id);
    public Product findBySlug(String slug);
    public void save(Product product);
    public void update(Product product);
    public void delete(String id);
}
