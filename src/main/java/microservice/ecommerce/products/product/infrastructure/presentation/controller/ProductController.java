package microservice.ecommerce.products.product.infrastructure.presentation.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.ExceptionHandler;
import lombok.RequiredArgsConstructor;
import microservice.ecommerce.products.product.application.dtos.PaginationProducts;
import microservice.ecommerce.products.product.application.dtos.Sort;
import microservice.ecommerce.products.product.application.ports.in.FindProductByIdUseCasePort;
import microservice.ecommerce.products.product.application.ports.in.FindProductBySlugUseCasePort;
import microservice.ecommerce.products.product.application.ports.in.SearchProductUseCasePort;
import microservice.ecommerce.products.product.domain.entity.Product;
import microservice.ecommerce.products.product.domain.exception.ProductNotFound;
import microservice.ecommerce.products.product.infrastructure.dtos.ResponsePayload;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final FindProductByIdUseCasePort findByIdUseCasePort;
    private final FindProductBySlugUseCasePort findBySlugUseCasePort;
    private final SearchProductUseCasePort searchProductUseCasePort;

    @GetMapping
    public ResponseEntity<PaginationProducts> searchProducts(
        @RequestParam(defaultValue = "") String query, 
        @RequestParam(defaultValue = "1") int page, 
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required = false) String sortBy,
        @RequestParam(defaultValue = "asc") String sortOrder,
        @RequestParam Map<String, String> filters
    ) {
        Sort sort = null;

        filters.remove("query");
        filters.remove("page");
        filters.remove("size");
        filters.remove("sortBy");
        filters.remove("sortOrder");

        if (sortBy != null) {
            sort = new Sort(sortBy, sortOrder);
        }

        return ResponseEntity.ok(
            searchProductUseCasePort.execute(query, sort, filters, page, size)
        );
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<ResponsePayload> getProductById(@PathVariable String id) {
        Product product = findByIdUseCasePort.execute(id);

        return ResponseEntity.ok(
            ResponsePayload.builder()
            .data(
                product
            ).build()
        );
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<ResponsePayload> getProductBySlug(@PathVariable String slug) {
        Product product = findBySlugUseCasePort.execute(slug);

        return ResponseEntity.ok(
            ResponsePayload.builder()
            .data(
                product
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
