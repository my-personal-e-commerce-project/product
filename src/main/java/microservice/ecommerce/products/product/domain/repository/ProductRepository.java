package microservice.ecommerce.products.product.domain.repository;

import microservice.ecommerce.products.product.domain.entity.Product;

public interface ProductRepository {
        
    public void save(Product product);
    public void update(Product product);
    public void delete(String id);
}
