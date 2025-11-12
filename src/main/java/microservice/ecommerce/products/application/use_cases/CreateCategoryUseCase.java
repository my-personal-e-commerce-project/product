package microservice.ecommerce.products.application.use_cases;

import microservice.ecommerce.products.application.ports.in.CreateCategoryUseCasePort;
import microservice.ecommerce.products.domain.entity.Category;
import microservice.ecommerce.products.domain.repository.CategoryRepository;

public class CreateCategoryUseCase implements CreateCategoryUseCasePort {

    private CategoryRepository categoryRepository;

    public CreateCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void execute(Category category) {
        categoryRepository.save(category);
    }
}
