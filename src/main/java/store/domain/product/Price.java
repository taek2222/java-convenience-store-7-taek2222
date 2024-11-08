package store.domain.product;

import static store.constant.ErrorMessage.INVALID_PRICE_NUMERIC;
import static store.constant.ErrorMessage.INVALID_PRICE_OUT_OF_RANGE;
import static store.validation.CommonValidator.validateNotNumeric;

import java.text.DecimalFormat;

public class Price {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###");
    private static final int MINIMUM_PRICE = 1;
    private static final int MAXIMUM_PRICE = 1_000_000;

    private final int price;

    public Price(final String inputPrice) {
        validateNotNumeric(inputPrice, INVALID_PRICE_NUMERIC);
        int price = Integer.parseInt(inputPrice);
        validateRange(price);
        this.price = price;
    }

    @Override
    public String toString() {
        return DECIMAL_FORMAT.format(price);
    }

    private void validateRange(final int price) {
        if (price < MINIMUM_PRICE || price > MAXIMUM_PRICE)
            throw new IllegalArgumentException(INVALID_PRICE_OUT_OF_RANGE.getMessage());
    }
}
