package store.domain.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PromotionsTest {

    @Test
    void 프로모션_객체의_정보를_반환한다() {
        // given
        Promotions promotions = new Promotions("테스트할인");

        // when
        String toString = promotions.toString();

        // then
        Assertions.assertThat(toString)
                .isEqualTo("테스트할인");
    }
}
