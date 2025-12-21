package microservice.ecommerce.products.category.application.ports.out;

import java.util.List;

import microservice.ecommerce.products.category.domain.entity.CategoryAttribute;

public interface CategoryReadRepository {
    
    public List<CategoryAttribute> getAttributesByIds(List<String> ids);
}
