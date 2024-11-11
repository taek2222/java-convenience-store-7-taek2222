package store.domain;

import static store.global.constant.MessageConstant.FORMAT;

import java.text.DecimalFormat;

public class PaymentProduct {

    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("###,###");

    private final String name;
    private final int quantity;
    private final int price;
    private final int promotion;

    public PaymentProduct(String name, int quantity, int price, int promotion) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        String formattedPrice = PRICE_FORMAT.format(quantity * price);
        return String.format(FORMAT, name, quantity, formattedPrice);
    }

    public boolean isPromotion() {
        return promotion != 0;
    }

    public String buildPromotion() {
        return String.format(FORMAT, name, promotion, "");
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalPrice() {
        return quantity * price;
    }

    public int getPromotionPrice() {
        return promotion * price;
    }
}
