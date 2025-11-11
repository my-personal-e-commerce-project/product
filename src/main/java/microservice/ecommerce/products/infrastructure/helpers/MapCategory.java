package microservice.ecommerce.products.infrastructure.helpers;

import microservice.ecommerce.products.domain.entity.Category;
import microservice.ecommerce.products.infrastructure.dtos.CategoryDto;

public class MapCategory {

    public static Category map(CategoryDto productDto) {
        return new Category(
            productDto.getId(),
            productDto.getName(),
            productDto.getSlug()
        );
    }
    
    public static CategoryDto fromCategory(Category category) {
        return new CategoryDto(
            category.id(),
            category.name(),
            category.slug()
        );
    }
}
