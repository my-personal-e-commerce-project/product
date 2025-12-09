package microservice.ecommerce.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import microservice.ecommerce.products.product.application.ports.in.CreateProductUseCasePort;
import microservice.ecommerce.products.product.application.ports.in.DeleteProductUseCasePort;
import microservice.ecommerce.products.product.application.ports.in.FindAllProductsByCategoryIdUseCasePort;
import microservice.ecommerce.products.product.application.ports.in.FindProductByIdUseCasePort;
import microservice.ecommerce.products.product.application.ports.in.FindProductBySlugUseCasePort;
import microservice.ecommerce.products.product.application.ports.in.SearchProductUseCasePort;
import microservice.ecommerce.products.product.application.ports.in.UpdateProductUseCasePort;
import microservice.ecommerce.products.product.application.use_cases.CreateProductUseCase;
import microservice.ecommerce.products.product.application.use_cases.DeleteProductUseCase;
import microservice.ecommerce.products.product.application.use_cases.FindAllProductsByCategoryIdUseCase;
import microservice.ecommerce.products.product.application.use_cases.FindProductByIdUseCase;
import microservice.ecommerce.products.product.application.use_cases.FindProductBySlugUseCase;
import microservice.ecommerce.products.product.application.use_cases.SearchProductUseCase;
import microservice.ecommerce.products.product.application.use_cases.UpdateProductUseCase;
import microservice.ecommerce.products.product.domain.repository.ProductRepository;
import microservice.ecommerce.products.product.infrastructure.mediator.Mediator;
import microservice.ecommerce.products.product.infrastructure.mediator.handler.CreatedProduct;
import microservice.ecommerce.products.product.infrastructure.mediator.handler.DeletedProduct;
import microservice.ecommerce.products.product.infrastructure.mediator.handler.UpdatedProduct;

@EnableDiscoveryClient
@SpringBootApplication
public class ProductsApplication {

    @Bean
    public SearchProductUseCasePort searchProductUseCasePort(
        ProductRepository productRepository
    ) {
        return new SearchProductUseCase(productRepository);
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
    public FindAllProductsByCategoryIdUseCasePort productRepository(
        ProductRepository productRepository
    ) {
        return new FindAllProductsByCategoryIdUseCase(productRepository);
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
        DeleteProductUseCasePort deleteProductUseCase
    ) {
        Mediator mediator = new Mediator();

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
