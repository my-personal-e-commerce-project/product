package microservice.ecommerce.products.category.application.use_cases;

import microservice.ecommerce.products.category.application.ports.in.DeleteCategoryUseCasePort;
import microservice.ecommerce.products.category.domain.repository.CategoryRepository;

public class DeleteCategoryUseCase implements DeleteCategoryUseCasePort{

    private CategoryRepository categoryRepository;

    public DeleteCategoryUseCase(
        CategoryRepository categoryRepository
    ) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void execute(String id) {
        categoryRepository.delete(id);
    }    
}
