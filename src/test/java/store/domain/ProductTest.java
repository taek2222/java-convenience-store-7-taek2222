package store.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    void 상품_정보_리스트로_상품을_등록한다() {
        // given
        List<String> product = List.of("콜라", "2000", "5", "테스트할인");

        // when & then
        assertThat(new Product(product))
                .isNotNull();
    }

    @Test
    void 단일_상품의_정보를_반환한다() {
        // given
        Product product = new Product(List.of("콜라", "2000", "5", "테스트할인"));

        // when
        List<String> info = product.buildInfo();

        // then
        assertThat(info)
                .containsExactly("콜라", "2,000", "5", "테스트할인");
    }
}
