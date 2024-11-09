package store.domain.product;

import static store.constant.ErrorMessage.INVALID_QUANTITY_NUMERIC;
import static store.constant.ErrorMessage.INVALID_QUANTITY_OUT_OF_RANGE;
import static store.validation.CommonValidator.validateNotNumeric;

import java.text.DecimalFormat;

public class Quantity {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###");

    private static final int MINIMUM_QUANTITY = 1;
    private static final int MAXIMUM_QUANTITY = 1_000_000;

    private int quantity;

    public Quantity(final String inputQuantity) {
        validateNotNumeric(inputQuantity, INVALID_QUANTITY_NUMERIC);
        int quantity = Integer.parseInt(inputQuantity);
        validateRange(quantity);
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        if (quantity == 0)
            return "재고 없음";
        return DECIMAL_FORMAT.format(quantity);
    }

    public void decreaseStock(final int quantity) {
        this.quantity -= quantity;
    }

    private void validateRange(final int quantity) {
        if (quantity < MINIMUM_QUANTITY || quantity > MAXIMUM_QUANTITY) {
            throw new IllegalArgumentException(INVALID_QUANTITY_OUT_OF_RANGE.getMessage());
        }
    }
}
