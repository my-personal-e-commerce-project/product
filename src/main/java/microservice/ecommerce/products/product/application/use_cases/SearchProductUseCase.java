package microservice.ecommerce.products.product.application.use_cases;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import microservice.ecommerce.products.category.domain.entity.AttributeDefinition;
import microservice.ecommerce.products.category.domain.repository.CategoryRepository;
import microservice.ecommerce.products.product.application.dtos.Filter;
import microservice.ecommerce.products.product.application.dtos.PaginationProducts;
import microservice.ecommerce.products.product.application.dtos.Sort;
import microservice.ecommerce.products.product.application.ports.in.SearchProductUseCasePort;
import microservice.ecommerce.products.product.application.ports.out.ProductReadRepository;

public class SearchProductUseCase implements SearchProductUseCasePort {

    private ProductReadRepository productRepository;
    private CategoryRepository categoryRepository;

    public SearchProductUseCase(ProductReadRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public PaginationProducts execute(
        String query, 
        Sort sort, 
        List<Filter> filters, 
        String category,
        int page, 
        int size
    ) {
        List<String> slugs = new ArrayList<>();
        
        if (filters != null) {
            filters.forEach(f -> slugs.add(f.attribute_definition_slug()));
        }

        if (sort != null && sort.attribute_definition_slug() != null) {
            slugs.add(sort.attribute_definition_slug());
        }

        Map<String, AttributeDefinition> attributes = 
            categoryRepository
            .getAttributeDefinitionBySlugs(slugs);

        if(filters != null)
            filters.forEach(a -> {
                AttributeDefinition attr = attributes.get(a.attribute_definition_slug());

                if(attr == null) {
                    throw new RuntimeException(
                        "Attribute " + a.attribute_definition_slug() + " not found"
                    );
                }

                attr.validTypeValue(
                    a.string_value(), 
                    a.integer_value(), 
                    a.double_value(), 
                    a.boolean_value()
                );
            });

        return productRepository
            .search(
                query, 
                category, 
                sort, 
                filters, 
                page, 
                size
            );
    }
}
