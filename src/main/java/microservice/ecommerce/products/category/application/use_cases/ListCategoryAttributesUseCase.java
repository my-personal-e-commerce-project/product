package microservice.ecommerce.products.category.application.use_cases;

import java.util.List;

import microservice.ecommerce.products.category.application.ports.in.ListCategoryAttributesUseCasePort;
import microservice.ecommerce.products.category.application.ports.out.CategoryReadRepository;
import microservice.ecommerce.products.category.domain.entity.CategoryAttribute;

public class ListCategoryAttributesUseCase implements ListCategoryAttributesUseCasePort {

    private final CategoryReadRepository categoryRepository;

    public ListCategoryAttributesUseCase(
        CategoryReadRepository categoryRepository
    ) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryAttribute> execute(List<String> categoryIds) {
        return categoryRepository.getAttributesByIds(categoryIds);
    }
}
