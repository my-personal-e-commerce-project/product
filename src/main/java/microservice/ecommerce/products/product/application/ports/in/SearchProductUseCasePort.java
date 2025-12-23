package microservice.ecommerce.products.product.application.ports.in;

import java.util.List;

import microservice.ecommerce.products.product.application.dtos.Filter;
import microservice.ecommerce.products.product.application.dtos.PaginationProducts;
import microservice.ecommerce.products.product.application.dtos.Sort;

public interface SearchProductUseCasePort {

    public PaginationProducts execute(String query, Sort sort, List<Filter> filters, String category, int page, int size);
}
