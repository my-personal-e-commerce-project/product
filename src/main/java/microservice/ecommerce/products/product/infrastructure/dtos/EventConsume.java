package microservice.ecommerce.products.product.infrastructure.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EventConsume {
    private String event;
    private Object payload;
}

