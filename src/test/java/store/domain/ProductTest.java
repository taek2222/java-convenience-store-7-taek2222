package store.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.domain.product.Name;
import store.domain.product.Price;
import store.domain.product.Quantity;

public class ProductTest {

    private Name name;
    private Price price;
    private Quantity quantity;
    private Promotions promotions;
    private Product product;

    @BeforeEach
    void setUp() {
        name = new Name("콜라");
        price = new Price("1000");
        quantity = new Quantity("10");
        product = new Product(name, price, quantity, promotions);
    }

    @Test
    void 재고를_감소시킬_때_부족한_수량을_반환한다() {
        // when
        int deficit = product.decreaseStock(15);

        // then
        assertThat(deficit).isEqualTo(5);
        assertThat(product.calculateRemainingStock(0)).isEqualTo(0);
    }

    @Test
    void 동일한_이름을_가진_상품을_확인한다() {
        // when
        boolean hasSameName = product.hasSameName("콜라");

        // then
        assertThat(hasSameName).isTrue();
    }

    @Test
    void 상품_이름을_반환한다() {
        // when
        String productName = product.getProductName();

        // then
        assertThat(productName).isEqualTo("콜라");
    }

    @Test
    void 상품_가격을_반환한다() {
        // when
        int productPrice = product.getProductPrice();

        // then
        assertThat(productPrice).isEqualTo(1000);
    }
}