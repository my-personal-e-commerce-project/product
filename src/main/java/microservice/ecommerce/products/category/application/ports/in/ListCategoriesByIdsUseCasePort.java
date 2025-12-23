package microservice.ecommerce.products.category.application.ports.in;

import java.util.List;

import microservice.ecommerce.products.category.domain.entity.Category;

public interface ListCategoriesByIdsUseCasePort {

    public List<Category> execute(List<String> categoryIds);
}
