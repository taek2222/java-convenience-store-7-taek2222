package store.domain.product;

import java.math.BigInteger;
import java.text.DecimalFormat;

public class Price {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###");

    private final BigInteger price;

    public Price(final String price) {
        this.price = new BigInteger(price);
    }

    @Override
    public String toString() {
        return DECIMAL_FORMAT.format(price);
    }
}
