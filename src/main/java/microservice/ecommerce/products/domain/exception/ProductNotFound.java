package microservice.ecommerce.products.domain.exception;

public class ProductNotFound extends RuntimeException {

    public ProductNotFound() {
        super("Product not found");
    }
}
