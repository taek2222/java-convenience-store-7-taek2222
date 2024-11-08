package store.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductsTest {

    @Test
    void 전체_상품의_정보를_반환한다() {
        // given
        Products products = new Products();
        products.addProduct(new Product(List.of("콜라", "2000", "5", "테스트할인1")));
        products.addProduct(new Product(List.of("사이다", "1500", "2", "테스트할인2")));

        // when
        List<List<String>> allProductsInfo = products.getAllProductsInfo();

        // then
        Assertions.assertThat(allProductsInfo).containsExactly(
                List.of("콜라", "2,000", "5", "테스트할인1"),
                List.of("사이다", "1,500", "2", "테스트할인2")
        );
    }
}
