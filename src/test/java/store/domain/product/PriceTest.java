package store.domain.product;

import static store.global.constant.ErrorMessage.INVALID_PRICE_NUMERIC;
import static store.global.constant.ErrorMessage.INVALID_PRICE_OUT_OF_RANGE;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @Test
    void 등록_가격이_숫자가_아닌_경우_예외가_발생한다() {
        Assertions.assertThatThrownBy(() -> new Price("가격"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_PRICE_NUMERIC.getMessage());
    }

    @ParameterizedTest(name = "테스트 가격 : [{arguments}]")
    @ValueSource(strings = {"0", "-1000"})
    void 등록_가격이_최소보다_작을_경우_예외가_발생한다(String price) {
        Assertions.assertThatThrownBy(() -> new Price(price))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_PRICE_OUT_OF_RANGE.getMessage());
    }

    @ParameterizedTest(name = "테스트 가격 : [{arguments}]")
    @ValueSource(strings = {"1000001", "1001000"})
    void 등록_가격이_최대보다_클_경우_예외가_발생한다(String price) {
        Assertions.assertThatThrownBy(() -> new Price(price))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_PRICE_OUT_OF_RANGE.getMessage());
    }
}
