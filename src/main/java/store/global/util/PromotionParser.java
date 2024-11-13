package store.global.util;

import java.util.List;
import store.domain.Promotions;

public class PromotionParser {

    public static List<Promotions> parseToPromotions(List<List<String>> input) {
        return input.stream()
                .map(PromotionParser::createPromotions)
                .toList();
    }

    private static Promotions createPromotions(List<String> promotionInfo) {
        return new Promotions(
                promotionInfo.get(0),
                promotionInfo.get(1),
                promotionInfo.get(2),
                promotionInfo.get(3),
                promotionInfo.get(4)
        );
    }
}
