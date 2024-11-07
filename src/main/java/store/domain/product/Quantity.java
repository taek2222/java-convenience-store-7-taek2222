package store.domain.product;

import java.text.DecimalFormat;

public class Quantity {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###");

    private final int quantity;

    public Quantity(String quantity) {
        this.quantity = Integer.parseInt(quantity);
    }

    @Override
    public String toString() {
        if (quantity == 0)
            return "재고 없음";
        return DECIMAL_FORMAT.format(quantity);
    }
}
