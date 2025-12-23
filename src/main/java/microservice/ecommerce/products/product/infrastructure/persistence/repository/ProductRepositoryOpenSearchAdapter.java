package microservice.ecommerce.products.product.infrastructure.persistence.repository;

import java.io.IOException;

import org.opensearch.client.opensearch.OpenSearchClient;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import microservice.ecommerce.products.product.domain.entity.Product;
import microservice.ecommerce.products.product.domain.repository.ProductRepository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryOpenSearchAdapter implements ProductRepository {

    private final OpenSearchClient client;

    @Override
    public void save(Product product) {
        try {
            client.index(i -> i
                .index("products_v1")
                .id(product.id())
                .document(product)
            );
        }
        catch(IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Product product) {
        try {
            client.index(i -> i
                .index("products_v1")
                .id(product.id())
                .document(product)
            );
        }
        catch(IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try{
            client.delete(d -> d
                .index("products_v1")
                .id(id)
            );
        } catch(IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
