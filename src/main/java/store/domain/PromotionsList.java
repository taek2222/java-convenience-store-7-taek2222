package store.domain;

import java.util.List;

public class PromotionsList {
    private List<Promotions> promotions;

    public PromotionsList(List<Promotions> inputPromotions) {
        this.promotions = inputPromotions;
    }

    public Promotions findPromotion(String name) {
        return promotions.stream()
                .filter(promotion -> promotion.hasName(name))
                .findFirst()
                .orElse(null);
    }
}
