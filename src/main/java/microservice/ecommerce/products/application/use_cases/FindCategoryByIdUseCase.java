package microservice.ecommerce.products.application.use_cases;

import microservice.ecommerce.products.application.ports.in.FindCategoryByIdUseCasePort;
import microservice.ecommerce.products.domain.entity.Category;
import microservice.ecommerce.products.domain.repository.CategoryRepository;

public class FindCategoryByIdUseCase implements FindCategoryByIdUseCasePort {

    private final CategoryRepository categoryRepository;

    public FindCategoryByIdUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category execute(String id) {
        return categoryRepository.findById(id);
    }
}
