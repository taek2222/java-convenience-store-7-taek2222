package store.domain.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PriceTest {

    @Test
    void 가격_객체의_정보를_반환한다() {
        // given
        Price price = new Price("2000");

        // when
        String toString = price.toString();

        // then
        Assertions.assertThat(toString)
                .isEqualTo("2,000");
    }
}
