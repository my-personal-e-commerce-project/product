package microservice.ecommerce.products.product.infrastructure.mediator;

import java.util.HashMap;
import java.util.Map;

public class Mediator {
    private final Map<String, Handler<?>> handlers = new HashMap<>();

    public <T> void register(String event, Handler<T> handler) {
        handlers.put(event, handler);
    }

    @SuppressWarnings("unchecked")
    public <T> void send(String type, T event) {
        Handler<T> handler = (Handler<T>) handlers.get(type);
        if (handler != null) {
            handler.handle(event);
        }
    }
}
