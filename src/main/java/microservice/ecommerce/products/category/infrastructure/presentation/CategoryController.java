package microservice.ecommerce.products.category.infrastructure.presentation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import microservice.ecommerce.products.category.application.ports.in.ListCategoryAttributesUseCasePort;
import microservice.ecommerce.products.category.domain.entity.CategoryAttribute;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
  
    private final ListCategoryAttributesUseCasePort listCategoryAttributesUseCasePort;
    
    @GetMapping
    public ResponseEntity<List<CategoryAttribute>> listCategoriesByIds(@RequestParam List<String> ids) {

        return ResponseEntity.ok(
            listCategoryAttributesUseCasePort.execute(ids)
        );
    }
}
