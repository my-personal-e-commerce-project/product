package microservice.ecommerce.products.product.infrastructure.persistence.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch._types.FieldValue;
import org.opensearch.client.opensearch._types.SortOptions;
import org.opensearch.client.opensearch._types.aggregations.Aggregate;
import org.opensearch.client.opensearch._types.aggregations.StringTermsAggregate;
import org.opensearch.client.opensearch._types.aggregations.StringTermsBucket;
import org.opensearch.client.opensearch._types.query_dsl.Query;
import org.opensearch.client.opensearch.core.GetResponse;
import org.opensearch.client.opensearch.core.SearchResponse;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import microservice.ecommerce.products.product.application.dtos.Filter;
import microservice.ecommerce.products.product.application.dtos.PaginationProducts;
import microservice.ecommerce.products.product.application.dtos.Sort;
import microservice.ecommerce.products.product.application.ports.out.ProductReadRepository;
import microservice.ecommerce.products.product.domain.entity.Product;
import microservice.ecommerce.products.shared.domain.exception.DataNotFound;

@Repository
@RequiredArgsConstructor
public class ProductReadRepositoryOpenSearchAdapter implements ProductReadRepository {

    private final OpenSearchClient client;
    private final String INDEX_NAME = "products_v1";

    @Override
    public Product findById(String id) {
        try {
            GetResponse<Product> response = client.get(g -> g
                .index("products")
                .id(id),
                Product.class
            );

            return response.found() ? response.source() : null;
        } catch (IOException e) {
            throw new DataNotFound("Product " + id + " not found");
        }
    }

    @Override
    public Product findBySlug(String slug) {
        try {
            SearchResponse<Product> response = client.search(s -> s
                .index("products")
                .query(q -> q
                    .term(t -> t
                        .field("slug")
                        .value(FieldValue.of(slug))
                    )
                )
                .size(1),
                Product.class
            );

            return response.hits().hits().isEmpty() 
                ? null 
                : response.hits().hits().get(0).source();
        } catch (IOException e) {
            throw new DataNotFound("Product " + slug + " not found");
        }
    }

    @Override
    public PaginationProducts search(
        String query, 
        String category, 
        Sort sort, 
        List<Filter> filters, 
        int page, 
        int size
    ) {

        try {
            SearchResponse<Product> response = client.search(s -> s
                .index(INDEX_NAME)
                .from(page * size)
                .size(size)
                .query(buildMainQuery(query, filters, category))
                .sort(buildSort(sort))
                .aggregations("categories", agg -> agg
                    .terms(t -> t.field("categories").size(100))
                ),
                Product.class
            );
            
            List<Product> products = response.hits().hits().stream()
                .map(hit -> hit.source())
                .toList();

            List<String> categoryIds = new ArrayList<>();
            
            Aggregate categoryAgg = response.aggregations().get("categories");
            
            if (categoryAgg != null) {
                StringTermsAggregate sterms = categoryAgg.sterms();
                List<StringTermsBucket> buckets = sterms.buckets().array();
                categoryIds = buckets.stream()
                    .<String>map(b -> b.key()) 
                    .toList();            
            }
            
            long total = response.hits().total().value();
            int lastPage = (int) Math.ceil((double) total / size) - 1;
            
            return new PaginationProducts(products, categoryIds, page, Math.max(0, lastPage));
        } catch (IOException e) {
            throw new RuntimeException("Error searching products", e);
        }
    }

    private Query buildMainQuery(String query, List<Filter> filters, String category) {
        if(
            (query == null || query.isBlank()) 
            && (filters == null || filters.isEmpty()) 
            && category == null
        ) {
            return Query.of(q -> q.matchAll(m -> m));
        }

        return Query.of(q -> q.bool(b -> {
            if (query != null && !query.isBlank()) {
                b.must(m -> m.multiMatch(mm -> mm
                    .query(query)
                    .fields("title^3", "description^2", "tags")
                ));
            }
          
            if (filters != null && !filters.isEmpty()) {
                for (Filter filter : filters) {
                    b.filter(buildOneFilter(filter));
                }
            }

            if(category != null)
                b.must(m -> m.term(t -> t
                    .field("categories")
                    .value(FieldValue.of(category))
                ));

            return b;
        }));
    }

    private Query buildOneFilter(Filter filter) {
        return Query.of(q -> q.nested(n -> n
            .path("attributes")
            .query(nq -> nq.bool(b -> {
                b.must(m -> m.term(t -> t
                    .field("attributes.attribute_definition_slug")
                    .value(FieldValue.of(filter.attribute_definition_slug()))
                ));
                
                if (filter.string_value() != null) {
                    b.must(m -> m.term(t -> t
                        .field("attributes.string_value")
                        .value(FieldValue.of(filter.string_value()))
                    ));
                } else if (filter.integer_value() != null) {
                    b.must(m -> m.term(t -> t
                        .field("attributes.integer_value")
                        .value(FieldValue.of(filter.integer_value()))
                    ));
                } else if (filter.double_value() != null) {
                    b.must(m -> m.term(t -> t
                        .field("attributes.double_value")
                        .value(FieldValue.of(filter.double_value()))
                    ));
                } else if (filter.boolean_value() != null) {
                    b.must(m -> m.term(t -> t
                        .field("attributes.boolean_value")
                        .value(FieldValue.of(filter.boolean_value()))
                    ));
                }
                
                return b;
            }))
        ));
    }

    private List<SortOptions> buildSort(Sort sort) {
        if (sort == null) return List.of();
        
        var order = sort.order().equalsIgnoreCase("asc") ? 
            org.opensearch.client.opensearch._types.SortOrder.Asc : 
            org.opensearch.client.opensearch._types.SortOrder.Desc;
        
        if (sort.attribute_definition_slug() == null || sort.attribute_definition_slug().isEmpty()) {
            return List.of(SortOptions.of(so -> so
                .field(f -> f.field(sort.attribute_definition_slug()).order(order))
            ));
        }
        
        String valueField = "attributes." + sort.sortBy() + "_value";
        
        return List.of(SortOptions.of(so -> so
            .field(f -> f
                .field(valueField)
                .order(order)
                .nested(n -> n
                    .path("attributes")
                    .filter(fn -> fn.term(t -> t
                        .field("attributes.attribute_definition")
                        .value(FieldValue.of(sort.attribute_definition_slug()))
                    ))
                )
            )
        ));
    }
}
