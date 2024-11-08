package store.domain.product;

import static store.constant.ErrorMessage.INVALID_PRICE_NUMERIC;
import static store.validation.CommonValidator.validateNotNumeric;

import java.text.DecimalFormat;

public class Price {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###");

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
        if (price < 1 || price > 1_000_000)
            throw new IllegalArgumentException();
    }
}
