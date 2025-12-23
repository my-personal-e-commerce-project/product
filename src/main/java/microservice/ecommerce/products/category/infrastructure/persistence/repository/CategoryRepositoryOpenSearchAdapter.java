package microservice.ecommerce.products.category.infrastructure.persistence.repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch._types.FieldValue;
import org.opensearch.client.opensearch.core.SearchResponse;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import microservice.ecommerce.products.category.domain.entity.AttributeDefinition;
import microservice.ecommerce.products.category.domain.entity.Category;
import microservice.ecommerce.products.category.domain.repository.CategoryRepository;
import microservice.ecommerce.products.category.infrastructure.persistence.model.CategoryModel;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryOpenSearchAdapter implements CategoryRepository {

    private final OpenSearchClient client;

    public Map<String, AttributeDefinition> getAttributeDefinitionBySlugs(
        List<String> attribute_slugs
    ) {
        Map<String, AttributeDefinition> attributes = new HashMap<>();

        try {
            SearchResponse<CategoryModel> response =
                client.search(
                    s -> s
                        .index("categories_v1")
                        .query(q -> q
                            .nested(n -> n
                                .path("attributes")
                                .query(nq -> nq
                                    .terms(t -> t
                                        .field("attributes.attribute_definition.slug")
                                        .terms(v -> v.value(
                                            attribute_slugs.stream()
                                                .map(FieldValue::of)
                                                .toList()
                                        ))
                                    )
                                )
                            )
                        ),
                    CategoryModel.class
                );

            response.hits().hits().forEach(hit -> {
                CategoryModel category = hit.source();
                if (category == null) return;

                category.getAttributes().forEach(attr -> {
                    AttributeDefinition def = attr.attribute_definition();
                    if (def == null) return;

                    String id = def.id();
                    if (attribute_slugs.contains(id)) {
                        attributes.putIfAbsent(id, def);
                    }
                });
            });

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return attributes;
    }

    @Override
    public void save(Category category) {
        try {
            client.index(i -> i
                .index("categories_v1")
                .id(category.id())
                .document(category)
            );
        }
        catch(IOException e) {
            throw new RuntimeException(e.getMessage());
        
        }
    }

    @Override
    public void update(Category category) {
        try {
            client.index(i -> i
                .index("categories_v1")
                .id(category.id())
                .document(category)
            );
        }
        catch(IOException e) {
            throw new RuntimeException(e.getMessage());
        
        }
    }

    @Override
    public void delete(String id) {
        try {
            client.delete(d -> d
                .index("categories_v1")
                .id(id)
            );
        }
        catch(IOException e) {
            throw new RuntimeException(e.getMessage());
        
        }
    }
}
