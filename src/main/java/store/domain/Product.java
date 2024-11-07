package store.domain;

import static store.constant.ErrorMessage.INVALID_NAME_BLANK;
import static store.constant.ErrorMessage.INVALID_NAME_NOT_NULL;
import static store.constant.ErrorMessage.INVALID_PRICE_TOO_LOW;

import java.math.BigInteger;

public class Product {

    private static final BigInteger MIN_PRICE = BigInteger.ONE;

    private final String name;
    private final BigInteger price;

    public Product(final String name, final BigInteger price) {
        validate(name, price);
        this.name = name.trim(); // 상품 이름 앞뒤 공백 제거
        this.price = price;
    }

    public BigInteger calculateTotalPrice(final int quantity) {
        return price.multiply(BigInteger.valueOf(quantity));
    }

    private void validate(final String name, final BigInteger price) {
        validateNull(name);
        validateName(name);
        validatePrice(price);
    }

    private void validateNull(final String name) {
        if (name == null) {
            throw new IllegalArgumentException(INVALID_NAME_NOT_NULL.getMessage());
        }
    }

    private void validateName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(INVALID_NAME_BLANK.getMessage());
        }
    }

    private void validatePrice(final BigInteger price) {
        if (price.compareTo(MIN_PRICE) < 0) {
            throw new IllegalArgumentException(INVALID_PRICE_TOO_LOW.getMessage());
        }
    }
}
