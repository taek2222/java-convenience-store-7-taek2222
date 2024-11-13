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

public class PurchaseProductTest {

    private Product product1;
    private Product product2;
    private PurchaseProduct purchaseProduct;

    @BeforeEach
    void setUp() {
        product1 = new Product(new Name("콜라"), new Price("1000"), new Quantity("10"), null);
        product2 = new Product(new Name("사이다"), new Price("1500"), new Quantity("5"), null);
        purchaseProduct = new PurchaseProduct(List.of(product1, product2), 5);
    }

    @Test
    void 상품_이름을_반환한다() {
        // when
        String productName = purchaseProduct.getProductName();

        // then
        assertThat(productName).isEqualTo("콜라");
    }

    @Test
    void 재고가_부족하면_예외를_던진다() {
        // when & then
        assertThatThrownBy(() -> new PurchaseProduct(List.of(product1), 15))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INSUFFICIENT_STOCK.getMessage());
    }
}