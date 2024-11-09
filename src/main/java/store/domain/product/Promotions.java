package store.domain.product;

public class Promotions {

    private static final String NAME_BLANK = "";

    private final String name;

    public Promotions(final String name) {
        this.name = sanitizeName(name);
    }

    @Override
    public String toString() {
        return name;
    }

    private String sanitizeName(final String name) {
        if (name.equals("null")) {
            return NAME_BLANK;
        }
        return name;
    }
}
