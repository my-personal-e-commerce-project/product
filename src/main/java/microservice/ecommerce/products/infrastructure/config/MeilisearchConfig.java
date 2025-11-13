package microservice.ecommerce.products.infrastructure.config;

import io.vanslog.spring.data.meilisearch.config.MeilisearchConfiguration;
import io.vanslog.spring.data.meilisearch.repository.config.EnableMeilisearchRepositories;
import io.vanslog.spring.data.meilisearch.client.ClientConfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;

@Configuration
@EnableMeilisearchRepositories(basePackages = "microservice.ecommerce.products.infrastructure.repository.meilisearch")
public class MeilisearchConfig extends MeilisearchConfiguration {

    @Value("${meilisearch.host-url}")
    private String meilisearchUrl;

    @Value("${meilisearch.api-key}")
    private String apiKey;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(meilisearchUrl)
                .withApiKey(apiKey)
                .build();
    }

    @Bean
    public Client meilisearch() {
        return new Client(new Config(meilisearchUrl, apiKey));
    }
}
