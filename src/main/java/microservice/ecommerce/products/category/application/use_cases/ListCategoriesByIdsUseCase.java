package microservice.ecommerce.products.category.application.use_cases;

import java.util.List;

import microservice.ecommerce.products.category.application.ports.in.ListCategoriesByIdsUseCasePort;
import microservice.ecommerce.products.category.application.ports.out.CategoryReadRepository;
import microservice.ecommerce.products.category.domain.entity.Category;

public class ListCategoriesByIdsUseCase implements ListCategoriesByIdsUseCasePort {

    private final CategoryReadRepository categoryRepository;

    public ListCategoriesByIdsUseCase(
        CategoryReadRepository categoryRepository
    ) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> execute(List<String> categoryIds) {
        return categoryRepository.getCategoriesByIds(categoryIds);
    }
}
