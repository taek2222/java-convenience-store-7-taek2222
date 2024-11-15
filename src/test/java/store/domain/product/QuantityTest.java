package store.domain.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static store.global.constant.ErrorMessage.INVALID_QUANTITY_NUMERIC;
import static store.global.constant.ErrorMessage.INVALID_QUANTITY_OUT_OF_RANGE;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class QuantityTest {

    @Test
    void 수량_객체의_정보를_반환한다2() {
        // given
        Quantity quantity = new Quantity("1000");

        // when
        String toString = quantity.toString();

        // then
        assertThat(toString)
                .isEqualTo("1,000개");
    }

    @Test
    void 수량이_0개일_경우_재고_없음을_반환한다() {
        // given
        Quantity quantity = new Quantity("0");

        // when
        String toString = quantity.toString();

        // then
        assertThat(toString)
                .isEqualTo("재고 없음");
    }

    @Test
    void 등록_수량이_숫자가_아닌_경우_예외가_발생한다() {
        assertThatThrownBy(() -> new Quantity("숫자"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_QUANTITY_NUMERIC.getMessage());
    }

    @ParameterizedTest(name = "테스트 수량 : [{arguments}]")
    @ValueSource(strings = {"-1", "-1000"})
    void 등록_수량이_최소보다_작을_경우_예외가_발생한다(String quantity) {
        assertThatThrownBy(() -> new Quantity(quantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_QUANTITY_OUT_OF_RANGE.getMessage());
    }

    @ParameterizedTest(name = "테스트 수량 : [{arguments}]")
    @ValueSource(strings = {"1000001", "1001000"})
    void 등록_수량이_최대보다_클_경우_예외가_발생한다(String quantity) {
        assertThatThrownBy(() -> new Quantity(quantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_QUANTITY_OUT_OF_RANGE.getMessage());
    }

    @Test
    void 재고를_감소시킨다() {
        // given
        Quantity quantity = new Quantity("1000");

        // when
        int deficit = quantity.decreaseStock(500);

        // then
        assertThat(quantity.toString()).isEqualTo("500개");
        assertThat(deficit).isEqualTo(0);
    }

    @Test
    void 재고를_감소시킬_때_부족한_수량을_반환한다() {
        // given
        Quantity quantity = new Quantity("1000");

        // when
        int deficit = quantity.decreaseStock(1500);

        // then
        assertThat(quantity.toString()).isEqualTo("재고 없음");
        assertThat(deficit).isEqualTo(500);
    }

    @Test
    void 수량_차이를_계산한다() {
        // given
        Quantity quantity = new Quantity("1000");

        // when
        int difference = quantity.calculateDifference(1500);

        // then
        assertThat(difference).isEqualTo(500);
    }
}
