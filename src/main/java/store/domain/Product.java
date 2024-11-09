package store.domain;

import static store.constant.ErrorMessage.INVALID_PRODUCT_ELEMENT;

import java.util.Arrays;
import java.util.List;
import store.domain.product.Name;
import store.domain.product.Price;
import store.domain.product.Promotions;
import store.domain.product.Quantity;

public class Product {

    private static final int ELEMENT_SIZE = 4;

    private final Name name;
    private final Price price;
    private final Quantity quantity;
    private final Promotions promotions;

    public Product(final List<String> product) {
        validateProductElementSize(product);

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

    public Product findEqualsName(String name) {
        if (this.name.toString().equals(name))
            return this;
        return null;
    }

    private void validateProductElementSize(List<String> product) {
        if (product.size() != ELEMENT_SIZE) {
            throw new IllegalArgumentException(INVALID_PRODUCT_ELEMENT.getMessage());
        }
    }
}
