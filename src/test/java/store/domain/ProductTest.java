package store.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import store.domain.product.Name;
import store.domain.product.Price;
import store.domain.product.Promotions;
import store.domain.product.Quantity;

public class ProductTest {

    @Test
    void 단일_상품의_정보를_반환한다() {
        // given
        Product product = new Product(
                new Name("콜라"),
                new Price("2000"),
                new Quantity("5"),
                new Promotions("테스트할인")
        );

        // when
        List<String> info = product.buildInfo();

        // then
        Assertions.assertThat(info)
                .containsExactly("콜라", "2,000", "5", "테스트할인");
    }
}
