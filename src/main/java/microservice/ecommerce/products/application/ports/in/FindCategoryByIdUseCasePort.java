package microservice.ecommerce.products.application.ports.in;

import microservice.ecommerce.products.domain.entity.Category;

public interface FindCategoryByIdUseCasePort {

    public Category execute(String id);
}
