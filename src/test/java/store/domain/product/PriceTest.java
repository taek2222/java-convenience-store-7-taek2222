package store.domain.product;

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

    @ParameterizedTest(name = "테스트 가격 : [{arguments}]")
    @ValueSource(ints = {0, -1000})
    void 등록_가격이_최소보다_작을_경우_예외가_발생한다(int price) {
        Assertions.assertThatThrownBy(() -> new Price(price))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "테스트 가격 : [{arguments}]")
    @ValueSource(ints = {1_000_000, 1_001_000})
    void 등록_가격이_최대보다_클_경우_예외가_발생한다(int price) {
        Assertions.assertThatThrownBy(() -> new Price(price))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 등록_가격이_숫자가_아닌_경우_예외가_발생한다() {
        Assertions.assertThatThrownBy(() -> new Price("가격"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
