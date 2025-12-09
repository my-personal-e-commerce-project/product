package microservice.ecommerce.products.product.domain.exception;

public class ProductNotFound extends RuntimeException {

    public ProductNotFound() {
        super("Product not found");
    }
}
