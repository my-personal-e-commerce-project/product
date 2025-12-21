package microservice.ecommerce.products.product.infrastructure.persistence.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.SearchRequest;
import com.meilisearch.sdk.model.Searchable;

import lombok.RequiredArgsConstructor;
import microservice.ecommerce.products.product.application.dtos.PaginationProducts;
import microservice.ecommerce.products.product.application.dtos.Sort;
import microservice.ecommerce.products.product.domain.entity.Product;
import microservice.ecommerce.products.product.domain.entity.ProductAttribute;
import microservice.ecommerce.products.product.domain.repository.ProductRepository;
import microservice.ecommerce.products.product.infrastructure.persistence.model.ProductModel;
import microservice.ecommerce.products.product.infrastructure.persistence.repository.meilisearch.ProductMeilisearchRepository;
import com.meilisearch.sdk.model.SearchResultPaginated;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryJpaAdapter implements ProductRepository {

    private final Client meilisearch;
    private final ObjectMapper objectMapper;
    private final ProductMeilisearchRepository productMeilisearchRepository;

    @Override
    public PaginationProducts search(String query, Sort sort, Map<String, String> filters, int page, int size) {
        Index index = meilisearch.index("products");

        SearchRequest searchRequest = new SearchRequest(query)
            .setPage(page)
            .setFilter(
                filters.entrySet().stream()
                    .map(entry -> entry.getKey() + " = \"" + entry.getValue() + "\"")
                    .toArray(String[]::new)
            )
            .setHitsPerPage(size)
            .setFacets(new String[]{"categories"});

        if(sort != null)
            searchRequest.setSort(new String[]{sort.sortBy()+":"+sort.order()});

        SearchResultPaginated searchResult = (SearchResultPaginated) index.search(searchRequest);
   
        List<Product> products = toProducts(searchResult.getHits());

        Object rawFacetDistribution = searchResult.getFacetDistribution();

        @SuppressWarnings("unchecked")
        Map<String, Map<String, Integer>> facetDistribution = (Map<String, Map<String, Integer>>) rawFacetDistribution;

        Map<String, Integer> categoriesCount = facetDistribution.get("categories");

        List<String> topCategories = categoriesCount.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(100)
                .map(Map.Entry::getKey)
                .toList();
       
        return new PaginationProducts(
            products, 
            topCategories, 
            page,
            searchResult.getTotalPages()
        );
    }

    @Override
    public Product findById(String id) {
        SearchRequest searchRequest = new SearchRequest("")
            .setFilter(new String[]{"id = '" + id + "'"});

        Searchable searchResult = meilisearch.index("products").search(searchRequest);

        List<Product> products = toProducts(searchResult.getHits());

        if (products.isEmpty()) {
            return null;
        }

        return products.get(0);
    }

    @Override
    public Product findBySlug(String slug) {
        SearchRequest searchRequest = new SearchRequest("")
            .setFilter(new String[]{"slug = '" + slug + "'"});

        Searchable searchResult = meilisearch.index("products").search(searchRequest);

        List<Product> products = toProducts(searchResult.getHits());

        if (products.isEmpty()) {
            return null;
        }

        return products.get(0);
    }

    @Override
    public void save(Product product) {
        
        productMeilisearchRepository.save(toDocument(product));
    }

    @Override
    public void update(Product product) {
        productMeilisearchRepository.save(toDocument(product));
    }

    @Override
    public void delete(String id) {
        productMeilisearchRepository.deleteById(id);
    }

    private ProductModel toDocument(Product product) {
        return new ProductModel(
            product.id(),
            product.title(),
            product.slug(),
            product.description(),
            product.categories(),
            product.price(),
            product.stock(),
            product.images(),
            product.attributes().stream().map(a -> {
                return new ProductAttribute(
                    a.attribute_definition(),
                    a.string_value(), 
                    a.integer_value(), 
                    a.double_value(),
                    a.boolean_value()
                );
            }).toList(),
            product.tags()
        );
    }

    private List<Product> toProducts(ArrayList<HashMap<String, Object>> hits) {

        try {
             List<Product> products = new ArrayList<>();
        
            for (HashMap<String, Object> hit : hits) {
                Product doc = objectMapper.convertValue(hit, Product.class);
                products.add(doc);
            }

            return products;
        }
        catch (Exception e) { 
            throw new RuntimeException("Error searching products", e);
        } 
    }

    private String[] toSortArray(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        
        String[] result = map.entrySet().stream()
            .filter(e -> e.getValue() != null && !e.getValue().isEmpty())
            .filter(e -> {
                String order = e.getValue().toLowerCase();
                return order.equals("asc") || order.equals("desc");
            })
            .map(e -> e.getKey() + ":" + e.getValue().toLowerCase())
            .toArray(String[]::new);
        
        return result.length > 0 ? result : null;
    }

    private String[] toFiltersArray(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        String[] result = map.entrySet().stream()
            .filter(e -> e.getValue() != null && !e.getValue().isEmpty())
            .map(e -> e.getKey() + " = '" + e.getValue() + "'")
            .toArray(String[]::new);
        
        return result.length > 0 ? result : null;
    }
}
