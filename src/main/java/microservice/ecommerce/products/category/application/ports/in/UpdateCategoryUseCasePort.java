package microservice.ecommerce.products.category.application.ports.in;

import microservice.ecommerce.products.category.domain.entity.Category;

public interface UpdateCategoryUseCasePort {

    public void execute(Category category);
}
