package microservice.ecommerce.products.product.application.ports.in;

import java.util.Map;

import microservice.ecommerce.products.product.application.dtos.PaginationProducts;
import microservice.ecommerce.products.product.application.dtos.Sort;

public interface SearchProductUseCasePort {

    public PaginationProducts execute(String query, Sort sort, Map<String, String> filters, int page, int size);
}
