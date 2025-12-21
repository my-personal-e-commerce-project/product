package microservice.ecommerce.products.product.domain.repository;

import java.util.Map;

import microservice.ecommerce.products.product.application.dtos.PaginationProducts;
import microservice.ecommerce.products.product.application.dtos.Sort;
import microservice.ecommerce.products.product.domain.entity.Product;

public interface ProductRepository {
    public PaginationProducts search(String query, Sort sort, Map<String, String> filters, int page, int size);
    public Product findById(String id);
    public Product findBySlug(String slug);
    public void save(Product product);
    public void update(Product product);
    public void delete(String id);
}
