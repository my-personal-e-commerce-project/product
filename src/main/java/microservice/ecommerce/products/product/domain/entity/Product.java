package microservice.ecommerce.products.product.domain.entity;

import java.util.List;

public class Product {

    private String id;
    private String name;
    private String slug;
    private String description;
    private Double price;
    private Integer stock;
    private List<String> images;
    private String category_id;

    public Product(
        String id,
        String name,
        String slug,
        String description,
        Double price,
        Integer stock,
        List<String> images,
        String category_id
    ) {

        this.id = id;
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.images = images;
        this.category_id = category_id;
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

    public String description() {
        return description;
    }

    public Double price() {
        return price;
    }

    public Integer stock() {
        return stock;
    }

    public List<String> images() {
        return images;
    }

    public String category_id() {
        return category_id;
    }
}
