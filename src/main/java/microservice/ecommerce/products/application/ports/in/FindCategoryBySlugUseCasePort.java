package microservice.ecommerce.products.application.ports.in;

import microservice.ecommerce.products.domain.entity.Category;

public interface FindCategoryBySlugUseCasePort {

    public Category execute(String slug);
}
