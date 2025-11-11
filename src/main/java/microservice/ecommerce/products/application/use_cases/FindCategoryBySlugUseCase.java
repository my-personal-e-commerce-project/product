package microservice.ecommerce.products.application.use_cases;

import microservice.ecommerce.products.application.ports.in.FindCategoryBySlugUseCasePort;
import microservice.ecommerce.products.domain.entity.Category;
import microservice.ecommerce.products.domain.repository.CategoryRepository;

public class FindCategoryBySlugUseCase implements FindCategoryBySlugUseCasePort {

    private final CategoryRepository categoryRepository;

    public FindCategoryBySlugUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category execute(String slug) {
        return categoryRepository.findBySlug(slug);
    }
}
