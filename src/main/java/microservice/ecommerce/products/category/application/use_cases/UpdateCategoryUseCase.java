package microservice.ecommerce.products.category.application.use_cases;

import microservice.ecommerce.products.category.application.ports.in.UpdateCategoryUseCasePort;
import microservice.ecommerce.products.category.domain.entity.Category;
import microservice.ecommerce.products.category.domain.repository.CategoryRepository;

public class UpdateCategoryUseCase implements UpdateCategoryUseCasePort{

    private CategoryRepository categoryRepository;

    public UpdateCategoryUseCase(
        CategoryRepository categoryRepository
    ) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void execute(Category category) {
        categoryRepository.update(category);
    }  
}
