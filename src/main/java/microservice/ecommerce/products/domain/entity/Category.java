package microservice.ecommerce.products.domain.entity;

public class Category {

    private Long id;
    private String name;
    private String slug;

    public Category(Long id, String name, String slug) {
        this.id = id;
        this.name = name;
        this.slug = slug;
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String slug() {
        return slug;
    }
}
