package microservice.ecommerce.products.application.use_cases;

import microservice.ecommerce.products.application.ports.in.UpdateCategoryUseCasePort;
import microservice.ecommerce.products.domain.entity.Category;
import microservice.ecommerce.products.domain.repository.CategoryRepository;

public class UpdateCategoryUseCase implements UpdateCategoryUseCasePort {

    private CategoryRepository categoryRepository;

    public UpdateCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void execute(Category category) {
        categoryRepository.update(category);
    }
}
