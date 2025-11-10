package microservice.ecommerce.products.infrastructure.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import microservice.ecommerce.products.infrastructure.dtos.ResponsePayload;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @GetMapping("/{id}")
    public EntityResponse<ResponsePayload> getProductById(@PathVariable String id) {
       
        return null;
    }

    @GetMapping("/{slug}/slug")
    public EntityResponse<ResponsePayload> getProductBySlug(@PathVariable String slug) {
        
        return null;
    }
}
