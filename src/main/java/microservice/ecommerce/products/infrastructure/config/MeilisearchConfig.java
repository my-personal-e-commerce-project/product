package microservice.ecommerce.products.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;

@Configuration
public class MeilisearchConfig {

    private String apiKey;

    private String hostUrl;

    @Bean
    public Client meilisearchClient() {
        return new Client(new Config(hostUrl, apiKey));
    }
}
