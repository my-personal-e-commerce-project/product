package microservice.ecommerce.products.infrastructure.mediator;

public interface Handler<T> {

    public void handle(T eventConsume);
}
