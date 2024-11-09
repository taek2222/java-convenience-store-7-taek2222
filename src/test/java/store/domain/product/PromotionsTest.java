package store.domain.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class PromotionsTest {

    @Test
    void 프로모션_객체의_정보를_반환한다() {
        // given
        Promotions promotions = new Promotions("테스트할인");

        // when
        String toString = promotions.toString();

        // then
        assertThat(toString)
                .isEqualTo("테스트할인");
    }

    @Test
    void 프로모션_null_일_때_빈값을_반환한다() {
        // given
        Promotions promotions = new Promotions("null");

        // when
        String toString = promotions.toString();

        // then
        assertThat(toString)
                .isEqualTo("");
    }
}
