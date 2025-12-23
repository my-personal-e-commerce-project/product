package microservice.ecommerce.products.product.infrastructure.persistence.repository.index;

import java.io.IOException;
import java.util.List;

import org.opensearch.client.opensearch.OpenSearchClient;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class ProductIndexInitializer {

    private final OpenSearchClient client;

    public ProductIndexInitializer(OpenSearchClient client) {
        this.client = client;
    }

    @PostConstruct
    void createIndex() throws IOException {
        String index = "products_v1";

        if (client.indices().exists(e -> e.index(index)).value()) {
            return;
        }

        client.indices().create(c -> c
            .index(index)
            .settings(s -> s
                .analysis(a -> a
                    .analyzer("spanish_analyzer", an -> an
                        .custom(ca -> ca
                            .tokenizer("standard")
                            .filter(List.of("lowercase", "asciifolding"))
                        )
                    )
                )
            )
            .mappings(m -> m
                .properties("id", p -> p.keyword(k -> k))
                .properties("slug", p -> p.keyword(k -> k))
                .properties("title", p -> p
                    .text(t -> t)
                )
                .properties("description", p -> p
                    .text(t -> t)
                )
                .properties("categories", p -> p.keyword(k -> k))
                .properties("tags", p -> p.keyword(k -> k))
                .properties("price", p -> p.double_(d -> d))
                .properties("stock", p -> p.integer(i -> i))
                .properties("images", p -> p.keyword(k -> k))
                .properties("attributes", p -> p
                    .nested(n -> n
                        .properties("attribute_definition_slug", a -> a.keyword(k -> k))
                        .properties("string_value", a -> a.keyword(k -> k))
                        .properties("integer_value", a -> a.integer(i -> i))
                        .properties("double_value", a -> a.double_(d -> d))
                        .properties("boolean_value", a -> a.boolean_(b -> b))
                    )
                )
            )
        );
    }
}
