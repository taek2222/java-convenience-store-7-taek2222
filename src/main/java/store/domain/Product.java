package store.domain;

public class Product {
    private String name;
    private Integer price;

    public Product(String name, Integer price) {
        validateName(name);
        this.name = name;
        this.price = price;
    }

    private void validateName(String name) {
        if (name.isBlank())
            throw new IllegalArgumentException();
    }
}
