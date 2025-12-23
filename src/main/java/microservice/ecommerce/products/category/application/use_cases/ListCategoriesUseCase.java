package microservice.ecommerce.products.category.application.use_cases;

import microservice.ecommerce.products.category.application.ports.in.ListCategoriesUseCasePort;
import microservice.ecommerce.products.category.application.ports.out.CategoryReadRepository;
import microservice.ecommerce.products.category.domain.entity.Category;
import microservice.ecommerce.products.shared.application.dtos.Pagination;

public class ListCategoriesUseCase implements ListCategoriesUseCasePort {

    private final CategoryReadRepository categoryReadRepository;

    public ListCategoriesUseCase(CategoryReadRepository categoryReadRepository) {
        this.categoryReadRepository = categoryReadRepository;
    }

    @Override
    public Pagination<Category> execute(String parent_id, int page, int limit) {
        return categoryReadRepository.findAll(parent_id, page, limit);
    }
}
