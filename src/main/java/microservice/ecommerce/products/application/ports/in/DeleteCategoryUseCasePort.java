package microservice.ecommerce.products.application.ports.in;

import java.util.Locale.Category;

public interface DeleteCategoryUseCasePort {

    public void execute(Category category);
}
