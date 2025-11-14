package microservice.ecommerce.products.infrastructure.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.SearchRequest;
import com.meilisearch.sdk.model.Searchable;

import lombok.RequiredArgsConstructor;
import microservice.ecommerce.products.domain.entity.Product;
import microservice.ecommerce.products.domain.repository.ProductRepository;
import microservice.ecommerce.products.infrastructure.model.ProductModel;
import microservice.ecommerce.products.infrastructure.repository.meilisearch.ProductMeilisearchRepository;
import com.meilisearch.sdk.model.SearchResultPaginated;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryAdapterImpl implements ProductRepository {

    private final Client meilisearch;
    private final ObjectMapper objectMapper;
    private final ProductMeilisearchRepository productMeilisearchRepository;

    @Override
    public List<Product> search(String query, int page, int size) {
        Index index = meilisearch.index("products");

        SearchRequest searchRequest = new SearchRequest(query)
            .setPage(page)
            .setHitsPerPage(size);

        SearchResultPaginated searchResult = (SearchResultPaginated) index.search(searchRequest);
      
        return toProducts(searchResult.getHits());
    }

    @Override
    public List<Product> findAllByCategory(String categoryId, int page, int size) {
        int offset = page * size;

        SearchRequest searchRequest = new SearchRequest("")
            .setFilter(new String[]{"category_id = '" + categoryId + "'"})
            .setLimit(size)
            .setOffset(offset);

        Searchable searchResult = meilisearch.index("products").search(searchRequest);

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
}
