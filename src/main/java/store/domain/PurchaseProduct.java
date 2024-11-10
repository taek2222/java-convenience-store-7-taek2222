package store.domain;

import static store.global.constant.ErrorMessage.INSUFFICIENT_STOCK;

import java.util.List;

public class PurchaseProduct {
    private final List<Product> purchase;

    public PurchaseProduct(List<Product> purchase) {
        this.purchase = purchase;
    }

    public void validateSufficientStock(int purchaseQuantity) {
        purchaseQuantity = calculateRemainingStock(purchaseQuantity);
        if(purchaseQuantity > 0)
            throw new IllegalArgumentException(INSUFFICIENT_STOCK.getMessage());
    }

    private int calculateRemainingStock(int purchaseQuantity) {
        return purchase.stream()
                .reduce(purchaseQuantity, (qty, product) -> product.calculateRemainingStock(qty), Integer::sum);
    }
}
