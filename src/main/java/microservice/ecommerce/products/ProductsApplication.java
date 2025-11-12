package microservice.ecommerce.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import microservice.ecommerce.products.application.ports.in.CreateCategoryUseCasePort;
import microservice.ecommerce.products.application.ports.in.CreateProductUseCasePort;
import microservice.ecommerce.products.application.ports.in.DeleteCategoryUseCasePort;
import microservice.ecommerce.products.application.ports.in.DeleteProductUseCasePort;
import microservice.ecommerce.products.application.ports.in.FindCategoryByIdUseCasePort;
import microservice.ecommerce.products.application.ports.in.FindCategoryBySlugUseCasePort;
import microservice.ecommerce.products.application.ports.in.FindProductByIdUseCasePort;
import microservice.ecommerce.products.application.ports.in.FindProductBySlugUseCasePort;
import microservice.ecommerce.products.application.ports.in.UpdateCategoryUseCasePort;
import microservice.ecommerce.products.application.ports.in.UpdateProductUseCasePort;
import microservice.ecommerce.products.application.use_cases.CreateCategoryUseCase;
import microservice.ecommerce.products.application.use_cases.CreateProductUseCase;
import microservice.ecommerce.products.application.use_cases.DeleteCategoryUseCase;
import microservice.ecommerce.products.application.use_cases.DeleteProductUseCase;
import microservice.ecommerce.products.application.use_cases.FindCategoryByIdUseCase;
import microservice.ecommerce.products.application.use_cases.FindCategoryBySlugUseCase;
import microservice.ecommerce.products.application.use_cases.FindProductByIdUseCase;
import microservice.ecommerce.products.application.use_cases.FindProductBySlugUseCase;
import microservice.ecommerce.products.application.use_cases.UpdateCategoryUseCase;
import microservice.ecommerce.products.application.use_cases.UpdateProductUseCase;
import microservice.ecommerce.products.domain.repository.CategoryRepository;
import microservice.ecommerce.products.domain.repository.ProductRepository;
import microservice.ecommerce.products.infrastructure.mediator.Mediator;
import microservice.ecommerce.products.infrastructure.mediator.handler.CreatedCategory;
import microservice.ecommerce.products.infrastructure.mediator.handler.CreatedProduct;
import microservice.ecommerce.products.infrastructure.mediator.handler.DeletedCategory;
import microservice.ecommerce.products.infrastructure.mediator.handler.DeletedProduct;
import microservice.ecommerce.products.infrastructure.mediator.handler.UpdatedCategory;
import microservice.ecommerce.products.infrastructure.mediator.handler.UpdatedProduct;

@SpringBootApplication
public class ProductsApplication {

    @Bean
    public DeleteCategoryUseCasePort createProductUseCase(
        CategoryRepository categoryRepository 
    ) {
        return new DeleteCategoryUseCase(categoryRepository);
    }

    @Bean
    public UpdateCategoryUseCasePort updateCategoryUseCase(
        CategoryRepository categoryRepository
    ) {
        return new UpdateCategoryUseCase(categoryRepository);
    }

    @Bean
    public CreateCategoryUseCasePort createCategoryUseCase(
        CategoryRepository categoryRepository
    ) {
        return new CreateCategoryUseCase(categoryRepository);
    }

    @Bean
    public FindCategoryByIdUseCasePort findCategoryByIdUseCasePort(
        CategoryRepository categoryRepository
    ) {
        return new FindCategoryByIdUseCase(categoryRepository);
    }

    @Bean
    public FindCategoryBySlugUseCasePort findCategoryBySlugUseCasePort(
        CategoryRepository categoryRepository
    ) {
        return new FindCategoryBySlugUseCase(categoryRepository);
    }

    @Bean
    public DeleteProductUseCasePort deleteProductUseCase(
        ProductRepository productRepository
    ) {
        return new DeleteProductUseCase(productRepository);
    }

    @Bean
    public UpdateProductUseCasePort updateProductUseCase(
        ProductRepository productRepository
    ) {
        return new UpdateProductUseCase(productRepository);
    }
    
    @Bean
    public CreateProductUseCasePort createProductUseCase(
        ProductRepository productRepository
    ) {
        return new CreateProductUseCase(productRepository);
    }

    @Bean
    public FindProductByIdUseCasePort findByIdUseCasePort(
        ProductRepository productRepository
    ) {
        return new FindProductByIdUseCase(productRepository);
    }

    @Bean
    public FindProductBySlugUseCasePort findBySlugUseCasePort(
        ProductRepository productRepository
    ) {
        return new FindProductBySlugUseCase(productRepository);
    }

    @Bean
    public Mediator mediator(
        CreateProductUseCasePort createProductUseCase,
        UpdateProductUseCasePort updateProductUseCase,
        DeleteProductUseCasePort deleteProductUseCase,
        CreateCategoryUseCasePort createCategoryUseCase,
        UpdateCategoryUseCasePort updateCategoryUseCase,
        DeleteCategoryUseCasePort deleteCategoryUseCase
    ) {
        Mediator mediator = new Mediator();

        mediator.register("category.created", new CreatedCategory(
            createCategoryUseCase
        ));

        mediator.register("category.updated", new UpdatedCategory(
            updateCategoryUseCase
        ));
        
        mediator.register("category.deleted", new DeletedCategory(
            deleteCategoryUseCase 
        ));

        mediator.register("product.created", new CreatedProduct(
            createProductUseCase
        ));

        mediator.register("product.updated", new UpdatedProduct(
            updateProductUseCase
        ));
        
        mediator.register("product.deleted", new DeletedProduct(
            deleteProductUseCase
        ));

        return mediator;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductsApplication.class, args);
    }
}
