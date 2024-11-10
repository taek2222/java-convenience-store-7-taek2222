package store.domain;

import java.util.ArrayList;
import java.util.List;

public class PurchaseList {
    private List<PurchaseProduct> purchases;

    public PurchaseList() {
        this.purchases = new ArrayList<>();
    }

    public void addPurchase(PurchaseProduct purchaseProduct) {
        purchases.add(purchaseProduct);
    }
}
