package store.domain;

import java.util.Arrays;
import java.util.List;
import store.domain.product.Name;
import store.domain.product.Price;
import store.domain.product.Promotions;
import store.domain.product.Quantity;

public class Product {
    private Name name;
    private Price price;
    private Quantity quantity;
    private Promotions promotions;

    public Product(Name name, Price price, Quantity quantity, Promotions promotions) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotions = promotions;
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
