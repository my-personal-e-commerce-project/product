package microservice.ecommerce.products.infrastructure.mediator;

import java.util.HashMap;
import java.util.Map;

import microservice.ecommerce.products.infrastructure.dtos.EventConsume;

public class Mediator {
    private final Map<String, Handler<?>> handlers = new HashMap<>();

    // Registrar un handler
    public <T> void register(String event, Handler<T> handler) {
        handlers.put(event, handler);
    }

    @SuppressWarnings("unchecked")
    public <T> void send(EventConsume<T> event) {
        Handler<T> handler = (Handler<T>) handlers.get(event.getEvent());
        if (handler != null) {
            handler.handle(event.getPayload());
        }
    }
}
