package microservice.ecommerce.products.category.infrastructure.persistence.repository.index;

import java.io.IOException;
import java.util.List;

import org.opensearch.client.opensearch.OpenSearchClient;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class CategoryIndexInitializer {
    private final OpenSearchClient client;

    public CategoryIndexInitializer(OpenSearchClient client) {
        this.client = client;
    }

    @PostConstruct
    void createIndex() throws IOException{
        String index = "categories_v1";
        
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
                .properties("name", p -> p
                    .text(t -> t.analyzer("spanish_analyzer"))
                )
                .properties("slug", p -> p.keyword(k -> k))
                .properties("parent_id", p -> p.keyword(k -> k))
                .properties("attributes", p -> p
                    .nested(n -> n
                        .properties("attribute_definition", a -> a
                            .object(o -> o
                                .properties("id", ap -> ap.keyword(k -> k))
                                .properties("name", ap -> ap.text(t -> t))
                                .properties("slug", ap -> ap.keyword(k -> k))
                                .properties("type", ap -> ap.keyword(k -> k))
                            )
                        )
                        .properties("is_required", a -> a.boolean_(b -> b))
                        .properties("is_filterable", a -> a.boolean_(b -> b))
                        .properties("is_sortable", a -> a.boolean_(b -> b))
                    )
                )
            )
        );
    }
}
