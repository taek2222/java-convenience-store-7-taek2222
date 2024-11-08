package store.domain.product;

public class Name {

    private final String name;

    public Name(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
