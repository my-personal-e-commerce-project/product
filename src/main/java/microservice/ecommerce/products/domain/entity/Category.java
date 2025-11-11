package microservice.ecommerce.products.domain.entity;

public class Category {

    private String id;
    private String name;
    private String slug;

    public Category(String id, String name, String slug) {
        this.id = id;
        this.name = name;
        this.slug = slug;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String slug() {
        return slug;
    }
}
