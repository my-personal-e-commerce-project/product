package microservice.ecommerce.products.product.infrastructure.mediator;

import java.util.HashMap;
import java.util.Map;

import microservice.ecommerce.products.product.infrastructure.dtos.EventConsume;

public class Mediator {
    private final Map<Class<? extends EventConsume>, Handler<?>> handlers = new HashMap<>();

    public <T> void register(Class<? extends EventConsume> event, Handler<T> handler) {
        handlers.put(event, handler);
    }

    @SuppressWarnings("unchecked")
    public <T> void send(T event) {
        Handler<T> handler = (Handler<T>) handlers.get(event.getClass());
        if (handler != null) {
            handler.handle(event);
        }
    }
}
