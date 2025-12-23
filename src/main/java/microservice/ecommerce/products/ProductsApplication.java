package microservice.ecommerce.products;

import org.apache.hc.core5.http.HttpHost;

import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.transport.OpenSearchTransport;
import org.opensearch.client.transport.rest_client.RestClientTransport;
import org.opensearch.client.json.jackson.JacksonJsonpMapper;
import org.opensearch.client.RestClient;

import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ProductsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductsApplication.class, args);
    }

    @Value("${OPENSEARCH_HOST}")
    private String OPENSEARCH_HOST;

    @Value("${OPENSEARCH_PORT}")
    private int OPENSEARCH_PORT;


    @Bean
    public OpenSearchClient openSearchClient() {
        RestClient restClient = RestClient.builder(
            new HttpHost(OPENSEARCH_HOST, OPENSEARCH_PORT)
        ).build();

        OpenSearchTransport transport =
            new RestClientTransport(restClient, new JacksonJsonpMapper());

        return new OpenSearchClient(transport);    
    }
}
