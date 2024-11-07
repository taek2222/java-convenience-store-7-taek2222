package store.domain;

import java.math.BigInteger;

public class Product {
    public static final String INVALID_NAME_NOT_BLANK = "물품 이름은 공백 및 빈칸일 수 없습니다.";
    public static final String INVALID_PRICE_MIN = "최소 금액(1원) 이상의 물품만 등록할 수 있습니다.";
    public static final String INVALID_NAME_NOT_NULL = "물품 이름은 NULL 일 수 없습니다.";

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
            throw new IllegalArgumentException(INVALID_NAME_NOT_NULL);
        }
    }

    private void validateName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(INVALID_NAME_NOT_BLANK);
        }
    }

    private void validatePrice(final BigInteger price) {
        if (price.compareTo(MIN_PRICE) < 0) {
            throw new IllegalArgumentException(INVALID_PRICE_MIN);
        }
    }
}
