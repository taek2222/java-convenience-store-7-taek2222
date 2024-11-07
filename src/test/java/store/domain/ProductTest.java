package store.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    void 이름과_가격으로_물품을_등록한다() {
        // given
        String name = "콜라";
        Integer price = 1_000;

        // when
        Product product = new Product(name, price);

        // then
        Assertions.assertThat(product)
                .isNotNull();
    }

    @Test
    void 이름으로_물품_가격을_반환한다() {
        // given
        String searchName = "콜라";
        Product product = new Product("콜라", 1_000);

        // when
        int price = product.getPriceByName(searchName);

        // then
        Assertions.assertThat(price).isEqualTo(1_000);
    }

    @Test
    void 등록_이름이_공백일_경우_예외가_발생한다() {
        // given
        String name = " ";
        Integer price = 1_000;

        // when & then
        Assertions.assertThatThrownBy(new Product(name, price))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 등록_가격이_0원보다_작을_경우_예외가_발생한다() {
        // given
        String name = "콜라";
        Integer price = 0;

        // when & then
        Assertions.assertThatThrownBy(new Product(name, price))
                .isInstanceOf(IllegalArgumentException.class);
    }
}























