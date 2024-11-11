package store.domain;

import static store.global.constant.ErrorMessage.INSUFFICIENT_STOCK;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class PurchaseProduct {
    private final List<Product> products;
    private int quantity;

    public PurchaseProduct(List<Product> products, int quantity) {
        this.products = products;
        validateSufficientStock(quantity);
        this.quantity = quantity;
    }

    public PaymentProduct createPaymentProduct(int i) {
        return new PaymentProduct(
                getProductName(),
                quantity,
                products.get(0).getProductPrice(),
                i
        );
    }

    public String getProductName() {
        return products.get(0).getProductName();
    }

    public int calculateRemainingStock() {
        return products.get(0).calculateRemainingStock(quantity);
    }

    public int calculatePromotionRate(int remainingStock) {
        return products.get(0).calculatePromotionRate(quantity - remainingStock);
    }

    public int calculate() {
        return products.get(0).calculate(quantity);
    }

    public boolean hasPromotionProduct() {
        return products.size() == 2;
    }

    private void validateSufficientStock(int purchaseQuantity) {
        int remainingStock = calculateTotalRemainingStock(purchaseQuantity);
        if (remainingStock > 0) {
            throw new IllegalArgumentException(INSUFFICIENT_STOCK.getMessage());
        }
    }

    private int calculateTotalRemainingStock(int purchaseQuantity) {
        return products.stream()
                .filter(Objects::nonNull)
                .reduce(purchaseQuantity, (qty, product) -> product.calculateRemainingStock(qty), Integer::sum);
    }

    public boolean isPromotionAdditionalProduct() {
        return products.get(0).isPromotionAdditionalProduct(quantity);
    }

    public void decrease(int quantity) {
        this.quantity -= quantity;
    }

    public boolean nodatelate(LocalDateTime now) {
        return products.getFirst().late(LocalDate.from(now));
    }
}