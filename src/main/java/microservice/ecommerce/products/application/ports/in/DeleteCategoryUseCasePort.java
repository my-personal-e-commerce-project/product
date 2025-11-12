package microservice.ecommerce.products.application.ports.in;

import microservice.ecommerce.products.domain.entity.Category;

public interface DeleteCategoryUseCasePort {

    public void execute(Category category);
}
