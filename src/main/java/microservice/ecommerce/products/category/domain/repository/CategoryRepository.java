package microservice.ecommerce.products.category.domain.repository;

import java.util.List;
import java.util.Map;

import microservice.ecommerce.products.category.domain.entity.AttributeDefinition;
import microservice.ecommerce.products.category.domain.entity.Category;

public interface CategoryRepository {

    public Map<String, AttributeDefinition> 
        getAttributeDefinitionBySlugs(List<String> attribute_slug);

    public void save(Category category);
    public void update(Category category);
    public void delete(String id);
}
