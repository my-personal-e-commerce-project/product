package microservice.ecommerce.products.category.infrastructure.persistence.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.SearchRequest;

import lombok.RequiredArgsConstructor;
import microservice.ecommerce.products.category.application.ports.out.CategoryReadRepository;
import microservice.ecommerce.products.category.domain.entity.Category;
import microservice.ecommerce.products.category.domain.entity.CategoryAttribute;

@Repository
@RequiredArgsConstructor
public class CategoryReadRepositoryJpaAdapter implements CategoryReadRepository {

    private final ObjectMapper objectMapper;
    private final Client meilisearch;

    @Override
    public List<CategoryAttribute> getAttributesByIds(List<String> ids) {
        Index index = meilisearch.index("categories");

        String filter = "id IN [" +
            ids.stream()
            .map(id -> "\"" + id + "\"")
            .collect(Collectors.joining(",")) +
            "]";

        SearchRequest searchRequest = new SearchRequest(null)
            .setFilter(new String[]{ filter });

        List<CategoryAttribute> attributes = new ArrayList<>();

        List<Category> categories = toCategories(index.search(searchRequest).getHits());

        categories.forEach(c -> {
            attributes.addAll(c.attributes());
        });

        System.out.println(attributes);
        
        return attributes;
    }

    private List<Category> toCategories(ArrayList<HashMap<String, Object>> hits) {

        try {
            List<Category> products = new ArrayList<>();

            for (HashMap<String, Object> hit : hits) {
                Category doc = objectMapper.convertValue(hit, Category.class);
                products.add(doc);
            }

            return products;
        }
        catch (Exception e) { 
            throw new RuntimeException("Error searching products", e);
        } 
    }
}
