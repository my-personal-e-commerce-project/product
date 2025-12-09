package microservice.ecommerce.products.product.infrastructure.model;

import java.util.List;

import org.springframework.data.annotation.Id;

import io.vanslog.spring.data.meilisearch.annotations.Document;
import io.vanslog.spring.data.meilisearch.annotations.Setting;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexUid = "products")
@Setting(
    filterableAttributes = {
        "id",
        "slug",
        "category_id",
    },
    searchableAttributes = {
        "name",
        "description",
    },
    sortableAttributes = {
        "price",
        "stock"
    }
)
public class ProductModel {

    @Id
    private String id;
    private String name;
    private String slug;
    private String description;
    private Double price;
    private Integer stock;
    private List<String> images;
    private String category_id;
}
