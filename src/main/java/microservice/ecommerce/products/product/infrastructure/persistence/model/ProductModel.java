package microservice.ecommerce.products.product.infrastructure.persistence.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import microservice.ecommerce.products.product.domain.entity.ProductAttribute;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductModel {

    private String id;
    private String title;
    private String slug;
    private String description;
    private List<String> categories;
    private double price;
    private int stock;
    private List<String> images;
    private List<ProductAttribute> attributes;
    private List<String> tags;
}
