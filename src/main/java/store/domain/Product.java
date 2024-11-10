package store.domain;

import store.domain.product.Name;
import store.domain.product.Price;
import store.domain.product.Quantity;

public class Product {

    private static final String INFO_DELIMITER = " ";

    private final Name name;
    private final Price price;
    private final Quantity quantity;
    private final Promotions promotions;

    public Product(Name name, Price price, Quantity quantity, Promotions promotions) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotions = promotions;
    }

    @Override
    public String toString() {
        String info = buildInfo();

        if (hasPromotion()) {
            return String.join(INFO_DELIMITER,
                    info,
                    promotions.toString());
        }
        return info;
    }

    public int calculateRemainingStock(int purchaseQuantity) {
        return quantity.calculateDifference(purchaseQuantity);
    }

    public boolean hasSameName(String name) {
        return this.name.toString().equals(name);
    }

    public boolean hasPromotion() {
        return this.promotions != null;
    }

    private String buildInfo() {
        return String.join(INFO_DELIMITER,
                name.toString(),
                price.toString(),
                quantity.toString()
        );
    }
}
