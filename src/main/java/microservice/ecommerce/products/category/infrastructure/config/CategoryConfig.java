package microservice.ecommerce.products.category.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import microservice.ecommerce.products.category.application.ports.in.CreateCategoryUseCasePort;
import microservice.ecommerce.products.category.application.ports.in.DeleteCategoryUseCasePort;
import microservice.ecommerce.products.category.application.ports.in.ListCategoryAttributesUseCasePort;
import microservice.ecommerce.products.category.application.ports.in.UpdateCategoryUseCasePort;
import microservice.ecommerce.products.category.application.ports.out.CategoryReadRepository;
import microservice.ecommerce.products.category.application.use_cases.CreateCategoryUseCase;
import microservice.ecommerce.products.category.application.use_cases.DeleteCategoryUseCase;
import microservice.ecommerce.products.category.application.use_cases.ListCategoryAttributesUseCase;
import microservice.ecommerce.products.category.application.use_cases.UpdateCategoryUseCase;
import microservice.ecommerce.products.category.domain.repository.CategoryRepository;

@Configuration
public class CategoryConfig {

    @Bean
    public ListCategoryAttributesUseCasePort listCategoryAttributesUseCasePort(
        CategoryReadRepository categoryRepository
    ) {
        return new ListCategoryAttributesUseCase(categoryRepository);
    }

    @Bean
    public CreateCategoryUseCasePort createCategoryUseCase(
        CategoryRepository categoryRepository
    ) {
        return new CreateCategoryUseCase(categoryRepository);
    }

    @Bean
    public UpdateCategoryUseCasePort updateCategoryUseCase(
        CategoryRepository categoryRepository
    ) {
        return new UpdateCategoryUseCase(categoryRepository);
    }

    @Bean
    public DeleteCategoryUseCasePort deleteCategoryUseCase(
        CategoryRepository categoryRepository
    ) {
        return new DeleteCategoryUseCase(categoryRepository);
    }
}
