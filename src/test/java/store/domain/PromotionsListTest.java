package store.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PromotionsListTest {

    private PromotionsList promotionsList;

    @BeforeEach
    void setUp() {
        Promotions promotion1 = new Promotions("할인1", "2", "1", "2023-01-01", "2023-12-31");
        Promotions promotion2 = new Promotions("할인2", "3", "1", "2023-01-01", "2023-12-31");
        promotionsList = new PromotionsList(List.of(promotion1, promotion2));
    }

    @Test
    void 프로모션을_이름으로_찾는다() {
        // when
        Promotions foundPromotion = promotionsList.findPromotion("할인1");

        // then
        assertThat(foundPromotion).isNotNull();
        assertThat(foundPromotion.toString()).isEqualTo("할인1");
    }

    @Test
    void 존재하지_않는_프로모션을_찾을_때_null을_반환한다() {
        // when
        Promotions foundPromotion = promotionsList.findPromotion("할인3");

        // then
        assertThat(foundPromotion).isNull();
    }
}