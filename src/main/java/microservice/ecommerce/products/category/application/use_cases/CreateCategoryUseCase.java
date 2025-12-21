package microservice.ecommerce.products.category.application.use_cases;

import microservice.ecommerce.products.category.application.ports.in.CreateCategoryUseCasePort;
import microservice.ecommerce.products.category.domain.entity.Category;
import microservice.ecommerce.products.category.domain.repository.CategoryRepository;

public class CreateCategoryUseCase implements CreateCategoryUseCasePort {

    private CategoryRepository categoryRepository;

    public CreateCategoryUseCase(
        CategoryRepository categoryRepository
    ) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void execute(Category category) {
        categoryRepository.save(category);
    }
}
