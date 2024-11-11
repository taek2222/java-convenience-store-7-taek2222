package store.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.domain.product.Name;
import store.domain.product.Price;
import store.domain.product.Quantity;
import store.global.constant.ErrorMessage;

public class ProductsTest {

    private Products products;

    @BeforeEach
    void setUp() {
        Product product1 = new Product(new Name("콜라"), new Price("1000"), new Quantity("10"), null);
        Product product2 = new Product(new Name("사이다"), new Price("1500"), new Quantity("5"), null);
        products = new Products(List.of(product1, product2));
    }

    @Test
    void 전체_상품_정보를_반환한다() {
        // when
        List<String> allProductsInfo = products.getAllProductsInfo();

        // then
        assertThat(allProductsInfo).containsExactly(
                "콜라 1,000원 10개",
                "사이다 1,500원 5개"
        );
    }

    @Test
    void 이름으로_상품을_찾는다() {
        // when
        List<Product> foundProducts = products.findProductByName("콜라");

        // then
        assertThat(foundProducts).hasSize(1);
        assertThat(foundProducts.get(0).getProductName()).isEqualTo("콜라");
    }

    @Test
    void 존재하지_않는_이름으로_상품을_찾을_때_예외가_발생한다() {
        // when & then
        assertThatThrownBy(() -> products.findProductByName("환타"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.PRODUCT_NOT_FOUND.getMessage());
    }
}