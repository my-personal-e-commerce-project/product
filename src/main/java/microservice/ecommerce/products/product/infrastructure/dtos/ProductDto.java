package microservice.ecommerce.products.product.infrastructure.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private String id;
    private String name;
    private String slug;
    private String description;
    private Integer stock;
    private Double price;
    private List<String> images;
    private String category_id;
}
