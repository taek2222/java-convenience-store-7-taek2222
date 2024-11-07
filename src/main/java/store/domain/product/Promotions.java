package store.domain.product;

public class Promotions {

    private final String name;

    public Promotions(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
