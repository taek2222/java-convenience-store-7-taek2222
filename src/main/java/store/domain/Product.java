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

    public Product(final String name, final String price, final String quantity, final String promotions) {
        this.name = new Name(name);
        this.price = new Price(price);
        this.quantity = new Quantity(quantity);
        this.promotions = new Promotions(promotions);
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
