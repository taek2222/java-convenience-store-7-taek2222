package store.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static store.constant.ErrorMessage.INVALID_NAME_BLANK;
import static store.constant.ErrorMessage.INVALID_NAME_NOT_NULL;
import static store.constant.ErrorMessage.INVALID_PRICE_TOO_LOW;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ProductTest {

    @Test
    void 이름과_가격으로_물품을_등록한다() {
        // given
        String name = "콜라";
        BigInteger price = BigInteger.valueOf(1_000);

        // when & then
        assertThat(new Product(name, price))
                .isNotNull();
    }

    @Test
    void 구매_수량의_물품_총가격을_반환한다() {
        // given
        Product product = new Product("콜라", BigInteger.valueOf(1_000));
        int quantity = 5;

        // when
        int totalPrice = product.calculateTotalPrice(quantity).intValue();

        // then
        assertThat(totalPrice)
                .isEqualTo(5_000);
    }

    @Test
    void 등록_이름이_NULL_일_경우_예외가_발생한다() {
        assertThatThrownBy(() -> new Product(null, BigInteger.ONE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_NAME_NOT_NULL.getMessage());
    }

    @ParameterizedTest(name = "테스트 이름 = [{arguments}]")
    @ValueSource(strings = {" ", ""})
    void 등록_이름이_공백일_경우_예외가_발생한다(String name) {
        assertThatThrownBy(() -> new Product(name, BigInteger.ONE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_NAME_BLANK.getMessage());
    }

    @ParameterizedTest(name = "테스트 가격 = [{arguments}]")
    @ValueSource(ints = {1, 1000})
    void 등록_가격이_최소보다_같거나_큰_경우_객체가_생성된다(int price) {
        BigInteger value = BigInteger.valueOf(price);
        assertThat(new Product("콜라", value))
                .isNotNull();
    }

    @ParameterizedTest(name = "테스트 가격 = [{arguments}]")
    @ValueSource(ints = {-1000, 0})
    void 등록_가격이_최소보다_작을_경우_예외가_발생한다(int price) {
        BigInteger value = BigInteger.valueOf(price);
        assertThatThrownBy(() -> new Product("콜라", value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_PRICE_TOO_LOW.getMessage());
    }
}
