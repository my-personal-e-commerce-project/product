package microservice.ecommerce.products.product.application.ports.out;

import java.util.List;

import microservice.ecommerce.products.product.application.dtos.Filter;
import microservice.ecommerce.products.product.application.dtos.PaginationProducts;
import microservice.ecommerce.products.product.application.dtos.Sort;
import microservice.ecommerce.products.product.domain.entity.Product;

public interface ProductReadRepository {
    public PaginationProducts search(String query, String category, Sort sort, List<Filter> filters, int page, int size);
    public Product findById(String id);
    public Product findBySlug(String slug);
}
