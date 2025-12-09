package microservice.ecommerce.products.product.infrastructure.dtos;

import java.time.Instant;

public interface EventConsume {

    public String eventName();
    public String aggregateId();
    public Instant occurredOn();
}
