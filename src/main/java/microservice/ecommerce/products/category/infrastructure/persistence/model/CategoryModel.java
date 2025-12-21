package microservice.ecommerce.products.category.infrastructure.persistence.model;

import java.util.List;

import org.springframework.data.annotation.Id;

import io.vanslog.spring.data.meilisearch.annotations.Document;
import io.vanslog.spring.data.meilisearch.annotations.Setting;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import microservice.ecommerce.products.category.domain.entity.CategoryAttribute;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexUid = "categories")
@Setting(
    filterableAttributes = {
        "id",
        "slug",
        "name",
        "parent_id"
    },
    searchableAttributes = {
        "name"
    },
    sortableAttributes = {
        "name"
    }
)
public class CategoryModel {

    @Id
    private String id;
    private String name;
    private String slug;
    private String parent_id;
    private List<CategoryAttribute> attributes;
}
