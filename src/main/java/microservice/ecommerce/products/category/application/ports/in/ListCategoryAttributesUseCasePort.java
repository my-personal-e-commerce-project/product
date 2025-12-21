package microservice.ecommerce.products.category.application.ports.in;

import java.util.List;

import microservice.ecommerce.products.category.domain.entity.CategoryAttribute;

public interface ListCategoryAttributesUseCasePort {

    public List<CategoryAttribute> execute(List<String> categoryIds);
}
