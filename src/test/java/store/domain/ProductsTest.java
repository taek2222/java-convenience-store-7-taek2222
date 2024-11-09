package store.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductsTest {

    @Test
    void 전체_상품_정보_리스트로_여러_상품을_등록한다() {
        // given
        List<List<String>> products = List.of(List.of("콜라", "2000", "5", "테스트할인1"),
                List.of("사이다", "1500", "2", "테스트할인2"));

        // when & then
        Assertions.assertThat(new Products(products))
                .isNotNull();
    }

    @Test
    void 전체_상품의_정보를_반환한다() {
        // given
        Products products = new Products(List.of(
                List.of("콜라", "2000", "5", "테스트할인1"),
                List.of("사이다", "1500", "2", "테스트할인2")
        ));

        // when
        List<List<String>> allProductsInfo = products.getAllProductsInfo();

        // then
        Assertions.assertThat(allProductsInfo).containsExactly(
                List.of("콜라", "2,000", "5", "테스트할인1"),
                List.of("사이다", "1,500", "2", "테스트할인2")
        );
    }

    @Test
    void 상품_목록에서_이름이_같은_상품을_찾는다() {
        // given
        String name = "콜라";
        List<String> product = List.of(name, "2000", "5", "테스트할인1");
        Products products = new Products(List.of(product));

        // when
        List<Product> result = products.findProductByEqualsName(name);

        // then
        Assertions.assertThat(result)
                .isEqualTo(product);
    }
}
