package store.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    void 이름과_가격으로_물품을_등록한다() {
        // given
        String name = "콜라";
        int price = 1_000;

        // when
        Product product = new Product(name, price);

        // then
        assertThat(product)
                .isNotNull();
    }

    @Test
    void 구매_수량의_물품_총가격을_반환한다() {
        // given
        String name = "콜라";
        int price = 1_000;
        int quantity = 5;
        Product product = new Product(name, price);

        // when
        int totalPrice = product.calculateTotalPrice(quantity);

        // then
        assertThat(totalPrice).isEqualTo(5_000);
    }

    @Test
    void 등록_이름이_공백일_경우_예외가_발생한다() {
        // given
        String name = " ";
        int price = 1_000;

        // when & then
        assertThatThrownBy(() -> new Product(name, price))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 등록_가격이_1원보다_작을_경우_예외가_발생한다() {
        // given
        String name = "콜라";
        int price = 0;

        // when & then
        assertThatThrownBy(() -> new Product(name, price))
                .isInstanceOf(IllegalArgumentException.class);
    }
}























