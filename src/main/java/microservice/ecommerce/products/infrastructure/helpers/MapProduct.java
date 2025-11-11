package microservice.ecommerce.products.infrastructure.helpers;

import microservice.ecommerce.products.domain.entity.Product;
import microservice.ecommerce.products.infrastructure.dtos.ProductDto;

public class MapProduct {

    public static Product map(ProductDto productDto) {
        return new Product(
            productDto.getId(),
            productDto.getName(),
            productDto.getSlug(),
            productDto.getDescription(),
            productDto.getPrice(),
            productDto.getStock(),
            productDto.getImages(),
            productDto.getCategory_id()
        );
    }

    public static ProductDto fromProduct(Product product) {
        return new ProductDto(
            product.id(),
            product.name(),
            product.slug(),
            product.description(),
            product.stock(),
            product.price(),
            product.images(),
            product.category_id()
        );
    }
}
