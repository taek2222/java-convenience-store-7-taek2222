package store.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class PromotionsTest {

    @Test
    void 프로모션_객체의_정보를_반환한다() {
        // given
        Promotions promotions = new Promotions("테스트할인", "2", "1", "2023-01-01", "2023-12-31");

        // when
        String toString = promotions.toString();

        // then
        assertThat(toString).isEqualTo("테스트할인");
    }

    @Test
    void 프로모션_적용_여부를_확인한다() {
        // given
        Promotions promotions = new Promotions("테스트할인", "2", "1", "2023-01-01", "2023-12-31");

        // when
        boolean isApplicable = promotions.isPromotionApplicable(5);

        // then
        assertThat(isApplicable).isTrue();
    }

    @Test
    void 남은_수량을_계산한다() {
        // given
        Promotions promotions = new Promotions("테스트할인", "2", "1", "2023-01-01", "2023-12-31");

        // when
        int remainingItems = promotions.calculateRemainingItems(5);

        // then
        assertThat(remainingItems).isEqualTo(2);
    }

    @Test
    void 프로모션_단위를_계산한다() {
        // given
        Promotions promotions = new Promotions("테스트할인", "2", "1", "2023-01-01", "2023-12-31");

        // when
        int promotionUnits = promotions.calculatePromotionUnits(5);

        // then
        assertThat(promotionUnits).isEqualTo(1);
    }

    @Test
    void 프로모션_기간_내_여부를_확인한다() {
        // given
        Promotions promotions = new Promotions("테스트할인", "2", "1", "2023-01-01", "2023-12-31");
        LocalDate date = LocalDate.of(2023, 6, 15);

        // when
        boolean isWithinPeriod = promotions.isWithinPromotionPeriod(date);

        // then
        assertThat(isWithinPeriod).isTrue();
    }

    @Test
    void 프로모션_기간_외_여부를_확인한다() {
        // given
        Promotions promotions = new Promotions("테스트할인", "2", "1", "2023-01-01", "2023-12-31");
        LocalDate date = LocalDate.of(2024, 1, 1);

        // when
        boolean isWithinPeriod = promotions.isWithinPromotionPeriod(date);

        // then
        assertThat(isWithinPeriod).isFalse();
    }
}