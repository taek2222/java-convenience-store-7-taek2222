package store.domain;

public class Product {
    private String name;
    private Integer price;

    public Product(String name, int price) {
        validateName(name);
        validatePrice(price);
        this.name = name;
        this.price = price;
    }

    public int calculateTotalPrice(int quantity) {
        return quantity * price;
    }

    private void validateName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    private void validatePrice(int price) {
        if (price < 1) {
            throw new IllegalArgumentException();
        }
    }
}
