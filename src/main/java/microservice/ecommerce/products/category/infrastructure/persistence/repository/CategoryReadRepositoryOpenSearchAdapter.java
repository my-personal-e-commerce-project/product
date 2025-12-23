package microservice.ecommerce.products.category.infrastructure.persistence.repository;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch._types.FieldValue;
import org.opensearch.client.opensearch._types.query_dsl.BoolQuery;
import org.opensearch.client.opensearch.core.SearchResponse;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import microservice.ecommerce.products.category.application.ports.out.CategoryReadRepository;
import microservice.ecommerce.products.category.domain.entity.Category;
import microservice.ecommerce.products.shared.application.dtos.Pagination;

@Repository
@RequiredArgsConstructor
public class CategoryReadRepositoryOpenSearchAdapter implements CategoryReadRepository {

    private final OpenSearchClient client;

    @Override
    public Pagination<Category> findAll(String parent_id, int page, int limit) {
                
        try {
            BoolQuery.Builder boolBuilder = new BoolQuery.Builder();

            if (parent_id != null) {
                boolBuilder.must(m -> m.term(t -> t
                    .field("parent_id")
                    .value(FieldValue.of(parent_id))
                ));
            }

            SearchResponse<Category> response = client.search(s -> s
                .index("categories_v1")
                .from(page * limit)
                .size(limit)
                .query(q -> q.bool(boolBuilder.build())),
                Category.class
            );
            
            List<Category> categories = response.hits().hits().stream()
                .map(hit -> hit.source())
                .toList();

            long total = response.hits().total().value();
            int lastPage = (int) Math.ceil((double) total / limit) - 1;
            
            return new Pagination<Category>(categories, page, lastPage);
        } catch (IOException e) {
            throw new RuntimeException("Error searching products", e);
        }
    }

    @Override
    public List<Category> getCategoriesByIds(List<String> ids) {
        try {
            List<FieldValue> values = ids.stream()
                .map(FieldValue::of)
                .toList();
           
            SearchResponse<Category> response = client.search(s -> s
                .index("categories_v1")
                .query(q -> q
                    .terms(t -> t.field("id").terms(v -> v.value(values)))
                ),
                Category.class
            );

            return response.hits().hits().stream()
                .map(hit -> hit.source())
                .filter(Objects::nonNull)
                .toList();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
