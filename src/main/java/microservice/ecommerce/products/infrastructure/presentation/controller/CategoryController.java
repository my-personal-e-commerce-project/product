package microservice.ecommerce.products.infrastructure.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import microservice.ecommerce.products.application.ports.in.FindCategoryByIdUseCasePort;
import microservice.ecommerce.products.application.ports.in.FindCategoryBySlugUseCasePort;
import microservice.ecommerce.products.domain.entity.Category;
import microservice.ecommerce.products.infrastructure.dtos.ResponsePayload;
import microservice.ecommerce.products.infrastructure.helpers.MapCategory;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final FindCategoryByIdUseCasePort findCategoryByIdUseCasePort;
    private final FindCategoryBySlugUseCasePort findCategoryBySlugUseCasePort;

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponsePayload> getCategoryById(@PathVariable String id) {
        Category category = findCategoryByIdUseCasePort.execute(id);

        return ResponseEntity.ok(ResponsePayload.builder().data(
            MapCategory.fromCategory(category)
        ).build());
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<ResponsePayload> getCategoryBySlug(@PathVariable String slug) {
        Category category = findCategoryBySlugUseCasePort.execute(slug);

        return ResponseEntity.ok(ResponsePayload.builder().data(
            MapCategory.fromCategory(category)
        ).build());    
    }
}
