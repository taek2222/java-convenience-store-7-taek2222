package store.domain;

import static store.global.constant.ErrorMessage.INSUFFICIENT_STOCK;

import java.util.List;
import java.util.Objects;

public class PurchaseProduct {
    private final List<Product> products;
    private final int quantity;

    public PurchaseProduct(List<Product> products, int quantity) {
        this.products = products;
        validateSufficientStock(quantity);
        this.quantity = quantity;
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
}