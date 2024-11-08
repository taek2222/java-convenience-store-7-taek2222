package store.domain;

import java.util.Arrays;
import java.util.List;
import store.domain.product.Name;
import store.domain.product.Price;
import store.domain.product.Promotions;
import store.domain.product.Quantity;

public class Product {
    private final Name name;
    private final Price price;
    private final Quantity quantity;
    private final Promotions promotions;

    public Product(final List<String> product) {
        this.name = new Name(product.get(0));
        this.price = new Price(product.get(1));
        this.quantity = new Quantity(product.get(2));
        this.promotions = new Promotions(product.get(3));
    }

    public List<String> buildInfo() {
        return Arrays.asList(
                name.toString(),
                price.toString(),
                quantity.toString(),
                promotions.toString()
        );
    }
}
