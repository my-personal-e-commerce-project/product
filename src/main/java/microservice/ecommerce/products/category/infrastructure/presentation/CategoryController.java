package microservice.ecommerce.products.category.infrastructure.presentation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import microservice.ecommerce.products.category.application.ports.in.ListCategoriesByIdsUseCasePort;
import microservice.ecommerce.products.category.application.ports.in.ListCategoriesUseCasePort;
import microservice.ecommerce.products.category.domain.entity.Category;
import microservice.ecommerce.products.shared.application.dtos.Pagination;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
  
    private final ListCategoriesByIdsUseCasePort listCategoriesByIdsUseCasePort;
    private final ListCategoriesUseCasePort listCategoriesUseCasePort;
   
    @GetMapping
    public ResponseEntity<Pagination<Category>> listCategories(
        @RequestParam(required = false) String parent_id, 
        @RequestParam(defaultValue = "1") int page, 
        @RequestParam(defaultValue = "10") int size
    ) {

        return ResponseEntity.ok(
            listCategoriesUseCasePort.execute(parent_id, page, size)
        );
    }

    @GetMapping
    @RequestMapping(name = "/attributes")
    public ResponseEntity<List<Category>> listCategoriesByIds(@RequestParam List<String> ids) {

        return ResponseEntity.ok(
            listCategoriesByIdsUseCasePort.execute(ids)
        );
    }
}
