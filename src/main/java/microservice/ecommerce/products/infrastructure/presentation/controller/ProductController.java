package microservice.ecommerce.products.infrastructure.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import microservice.ecommerce.products.application.ports.in.FindProductByIdUseCasePort;
import microservice.ecommerce.products.application.ports.in.FindProductBySlugUseCasePort;
import microservice.ecommerce.products.domain.entity.Product;
import microservice.ecommerce.products.infrastructure.dtos.ResponsePayload;
import microservice.ecommerce.products.infrastructure.helpers.MapProduct;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final FindProductByIdUseCasePort findByIdUseCasePort;
    private final FindProductBySlugUseCasePort findBySlugUseCasePort;

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponsePayload> getProductById(@PathVariable String id) {
        Product product = findByIdUseCasePort.execute(id);

        return ResponseEntity.ok(
            ResponsePayload.builder()
            .data(
                MapProduct.fromProduct(product)
            ).build()
        );
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<ResponsePayload> getProductBySlug(@PathVariable String slug) {
        Product product = findBySlugUseCasePort.execute(slug);

        return ResponseEntity.ok(
            ResponsePayload.builder()
            .data(
                MapProduct.fromProduct(product)
            ).build()
        );    
    }
}
