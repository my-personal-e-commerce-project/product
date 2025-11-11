package microservice.ecommerce.products.infrastructure.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "categories")
public class CategoryModel {

    private String id;
    private String name;
    private String slug;
}
