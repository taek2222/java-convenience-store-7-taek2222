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

    private void validate(String name, int price) {
        validateNull(name);
        validateName(name);
        validatePrice(price);
    }

    private void validateNull(final String name) {
        if (name == null) {
            throw new IllegalArgumentException(INVALID_NAME_NOT_NULL);
        }
    }

    private void validateName(final String name) {
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
