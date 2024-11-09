package store.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static store.constant.ErrorMessage.INVALID_PRODUCT_ELEMENT;

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

    @Test
    void 상품_정보_리스트_크기가_4가_아닌_경우_예외가_발생한다() {
        // given
        List<String> product = List.of("콜라", "2000", "5");

        // when & then
        assertThatThrownBy(() -> new Product(product))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_PRODUCT_ELEMENT.getMessage());
    }

    @Test
    void 이름이_같은_경우_상품을_반환한다() {
        // given
        String name = "콜라";
        Product product = new Product(List.of(name, "2000", "5", "테스트할인"));

        // when
        Product result = product.findEqualsName(name);

        // then
        assertThat(result).isEqualTo(product);
    }
}
