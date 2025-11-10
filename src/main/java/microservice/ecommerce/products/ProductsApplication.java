package microservice.ecommerce.products;

import javax.print.attribute.standard.Media;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import microservice.ecommerce.products.application.ports.in.FindProductByIdUseCasePort;
import microservice.ecommerce.products.application.ports.in.FindProductBySlugUseCasePort;
import microservice.ecommerce.products.application.use_cases.FindProductByIdUseCase;
import microservice.ecommerce.products.application.use_cases.FindProductBySlugUseCase;
import microservice.ecommerce.products.domain.repository.ProductRepository;
import microservice.ecommerce.products.infrastructure.mediator.Mediator;
import microservice.ecommerce.products.infrastructure.mediator.handler.CreatedProduct;
import microservice.ecommerce.products.infrastructure.mediator.handler.DeletedProduct;
import microservice.ecommerce.products.infrastructure.mediator.handler.UpdatedProduct;

@SpringBootApplication
public class ProductsApplication {

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
    public Mediator mediator() {
        Mediator mediator = new Mediator();

        mediator.register("product.created", new CreatedProduct());
        mediator.register("product.updated", new UpdatedProduct());
        mediator.register("product.deleted", new DeletedProduct());

        return mediator;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductsApplication.class, args);
    }
}
