package microservice.ecommerce.products.product.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import microservice.ecommerce.products.category.domain.repository.CategoryRepository;
import microservice.ecommerce.products.product.application.ports.in.CreateProductUseCasePort;
import microservice.ecommerce.products.product.application.ports.in.DeleteProductUseCasePort;
import microservice.ecommerce.products.product.application.ports.in.FindProductByIdUseCasePort;
import microservice.ecommerce.products.product.application.ports.in.FindProductBySlugUseCasePort;
import microservice.ecommerce.products.product.application.ports.in.SearchProductUseCasePort;
import microservice.ecommerce.products.product.application.ports.in.UpdateProductUseCasePort;
import microservice.ecommerce.products.product.application.ports.out.ProductReadRepository;
import microservice.ecommerce.products.product.application.use_cases.CreateProductUseCase;
import microservice.ecommerce.products.product.application.use_cases.DeleteProductUseCase;
import microservice.ecommerce.products.product.application.use_cases.FindProductByIdUseCase;
import microservice.ecommerce.products.product.application.use_cases.FindProductBySlugUseCase;
import microservice.ecommerce.products.product.application.use_cases.SearchProductUseCase;
import microservice.ecommerce.products.product.application.use_cases.UpdateProductUseCase;
import microservice.ecommerce.products.product.domain.repository.ProductRepository;

@Configuration
public class ConfigProduct {
    
    @Bean
    public SearchProductUseCasePort searchProductUseCasePort(
        ProductReadRepository productReadRepository,
        CategoryRepository categoryRepository
    ) {
        return new SearchProductUseCase(
            productReadRepository, 
            categoryRepository
        );
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
        ProductReadRepository productReadRepository
    ) {
        return new FindProductByIdUseCase(productReadRepository);
    }

    @Bean
    public FindProductBySlugUseCasePort findBySlugUseCasePort(
        ProductReadRepository productReadRepository
    ) {
        return new FindProductBySlugUseCase(productReadRepository);
    }
}
