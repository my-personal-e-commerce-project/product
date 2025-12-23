package microservice.ecommerce.products.category.infrastructure.persistence.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import microservice.ecommerce.products.category.domain.entity.CategoryAttribute;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel {

    private String id;
    private String name;
    private String slug;
    private String parent_id;
    private List<CategoryAttribute> attributes;
}
