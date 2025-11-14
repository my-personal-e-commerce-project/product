package microservice.ecommerce.products.infrastructure.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.ExceptionHandler;
import lombok.RequiredArgsConstructor;
import microservice.ecommerce.products.application.ports.in.FindAllProductsByCategoryIdUseCasePort;
import microservice.ecommerce.products.application.ports.in.FindProductByIdUseCasePort;
import microservice.ecommerce.products.application.ports.in.FindProductBySlugUseCasePort;
import microservice.ecommerce.products.application.ports.in.SearchProductUseCasePort;
import microservice.ecommerce.products.domain.entity.Product;
import microservice.ecommerce.products.domain.exception.ProductNotFound;
import microservice.ecommerce.products.infrastructure.dtos.ResponsePayload;
import microservice.ecommerce.products.infrastructure.helpers.MapProduct;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final FindProductByIdUseCasePort findByIdUseCasePort;
    private final FindProductBySlugUseCasePort findBySlugUseCasePort;
    private final SearchProductUseCasePort searchProductUseCasePort;
    private final FindAllProductsByCategoryIdUseCasePort findAllProductsByCategoryIdUseCasePort;

    @GetMapping("/category/{id}")
    public ResponseEntity<ResponsePayload> getProductsByCategory(
        @PathVariable String id, 
        @RequestParam(defaultValue = "1") int page, 
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(
            ResponsePayload.builder()
            .data(
                findAllProductsByCategoryIdUseCasePort.execute(id, page, size)
            ).build()
        );
    }

    @GetMapping
    public ResponseEntity<ResponsePayload> searchProducts(
        @RequestParam(defaultValue = "") String query, 
        @RequestParam(defaultValue = "1") int page, 
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(
            ResponsePayload.builder()
            .data(
                searchProductUseCasePort.execute(query, page, size)
                    .stream()
                    .map(MapProduct::fromProduct)
                    .toList()
            ).build()
        );
    }
    
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

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ResponsePayload> handleException(ProductNotFound e) {
        return new ResponseEntity<>(
            ResponsePayload.builder()
                .message(e.getMessage())
                .build(),
            HttpStatus.NOT_FOUND
        );
    }
}
