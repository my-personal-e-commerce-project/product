package microservice.ecommerce.products.application.use_cases;

import microservice.ecommerce.products.application.ports.in.DeleteCategoryUseCasePort;
import microservice.ecommerce.products.domain.entity.Category;
import microservice.ecommerce.products.domain.repository.CategoryRepository;

public class DeleteCategoryUseCase implements DeleteCategoryUseCasePort {

    private CategoryRepository categoryRepository;

    public DeleteCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void execute(Category category) {
        categoryRepository.delete(category.id());
        
    }
}
