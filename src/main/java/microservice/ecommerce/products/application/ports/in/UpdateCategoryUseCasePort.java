package microservice.ecommerce.products.application.ports.in;

import microservice.ecommerce.products.domain.entity.Category;

public interface UpdateCategoryUseCasePort {

    public void execute(Category category);
}
