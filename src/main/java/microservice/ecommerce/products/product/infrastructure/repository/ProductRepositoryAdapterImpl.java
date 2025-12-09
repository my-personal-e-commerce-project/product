package microservice.ecommerce.products.product.infrastructure.repository;

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
import microservice.ecommerce.products.product.domain.entity.Product;
import microservice.ecommerce.products.product.domain.repository.ProductRepository;
import microservice.ecommerce.products.product.infrastructure.model.ProductModel;
import microservice.ecommerce.products.product.infrastructure.repository.meilisearch.ProductMeilisearchRepository;
import com.meilisearch.sdk.model.SearchResultPaginated;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryAdapterImpl implements ProductRepository {

    private final Client meilisearch;
    private final ObjectMapper objectMapper;
    private final ProductMeilisearchRepository productMeilisearchRepository;

    @Override
    public List<Product> search(String query, Map<String, String> sort, int page, int size) {
        Index index = meilisearch.index("products");

        SearchRequest searchRequest = new SearchRequest(query)
            .setPage(page)
            .setHitsPerPage(size);

        SearchResultPaginated searchResult = (SearchResultPaginated) index.search(searchRequest);
      
        return toProducts(searchResult.getHits());
    }

    @Override
    public List<Product> findAllByFilters(Map<String, String> filters, Map<String, String> sort, int page, int size) {

        SearchRequest searchRequest = new SearchRequest("")
            .setPage(page)
            .setHitsPerPage(size);

        String[] filtersArray = toFiltersArray(filters);
        if (filtersArray != null)
            searchRequest.setFilter(filtersArray);

        String[] sortArray = toSortArray(sort);
        if (sortArray != null)
            searchRequest.setSort(sortArray);

        Searchable searchResult = meilisearch
            .index("products")
            .search(searchRequest);

        return toProducts(searchResult.getHits());
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
            product.name(),
            product.slug(),
            product.description(),
            product.price(),
            product.stock(),
            product.images(),
            product.category_id()
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
