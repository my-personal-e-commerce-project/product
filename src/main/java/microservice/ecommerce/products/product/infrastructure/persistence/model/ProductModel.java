package microservice.ecommerce.products.product.infrastructure.persistence.model;

import java.util.List;

import org.springframework.data.annotation.Id;

import io.vanslog.spring.data.meilisearch.annotations.Document;
import io.vanslog.spring.data.meilisearch.annotations.Setting;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import microservice.ecommerce.products.product.domain.entity.ProductAttribute;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexUid = "products")
@Setting(
    filterableAttributes = {
        "id",
        "slug",
        "categories"
    },
    searchableAttributes = {
        "title",
        "description",
        "tags"
    },
    sortableAttributes = {
        "price",
        "stock"
    }
)
public class ProductModel {

    @Id
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
