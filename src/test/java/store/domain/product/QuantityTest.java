package store.domain.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuantityTest {

    @Test
    void 수량_객체의_정보를_반환한다1() {
        // given
        Quantity quantity = new Quantity("1");

        // when
        String toString = quantity.toString();

        // then
        Assertions.assertThat(toString)
                .isEqualTo("1");
    }

    @Test
    void 수량_객체의_정보를_반환한다2() {
        // given
        Quantity quantity = new Quantity("1000");

        // when
        String toString = quantity.toString();

        // then
        Assertions.assertThat(toString)
                .isEqualTo("1,000");
    }

    @Test
    void 수량이_0개일_경우_재고_없음을_반환한다() {
        // given
        Quantity quantity = new Quantity("0");

        // when
        String toString = quantity.toString();

        // then
        Assertions.assertThat(toString)
                .isEqualTo("재고 없음");
    }
}
