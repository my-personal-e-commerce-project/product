package microservice.ecommerce.products.infrastructure.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EventConsume {
    private String event;
    private Object payload;
}

