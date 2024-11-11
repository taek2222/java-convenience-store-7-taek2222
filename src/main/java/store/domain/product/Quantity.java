package store.domain.product;

import static store.global.constant.ErrorMessage.INVALID_QUANTITY_NUMERIC;
import static store.global.constant.ErrorMessage.INVALID_QUANTITY_OUT_OF_RANGE;
import static store.global.validation.CommonValidator.validateNotNumeric;

import java.text.DecimalFormat;

public class Quantity {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###");

    private static final int MINIMUM_QUANTITY = 0;
    private static final int MAXIMUM_QUANTITY = 1_000_000;
    private static final String UNIT = "개";

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
        return DECIMAL_FORMAT.format(quantity) + UNIT;
    }

    public int decreaseStock(int quantity) {
        int remainingQuantity = this.quantity - quantity;
        if (remainingQuantity < 0) {
            int deficit = -remainingQuantity;
            this.quantity = 0;
            return deficit;
        }
        this.quantity = remainingQuantity;
        return 0;
    }

    public int calculateDifference(int quantity) {
        return quantity - this.quantity;
    }

    private void validateRange(final int quantity) {
        if (quantity < MINIMUM_QUANTITY || quantity > MAXIMUM_QUANTITY) {
            throw new IllegalArgumentException(INVALID_QUANTITY_OUT_OF_RANGE.getMessage());
        }
    }
}
