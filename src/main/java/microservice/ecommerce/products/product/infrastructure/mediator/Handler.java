package microservice.ecommerce.products.product.infrastructure.mediator;

public interface Handler<T> {

    public void handle(T eventConsume);
}
