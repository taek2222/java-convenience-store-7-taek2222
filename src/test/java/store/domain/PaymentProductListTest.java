package store.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PaymentProductListTest {

    private PaymentProductList paymentProductList;

    @BeforeEach
    void setUp() {
        paymentProductList = new PaymentProductList();
    }

    @Test
    void 상품을_추가한다() {
        // given
        PaymentProduct product = new PaymentProduct("콜라", 2, 1000, 0);

        // when
        paymentProductList.addPaymentProduct(product);

        // then
        assertThat(paymentProductList.getAllProductQuantities()).containsExactly(2);
    }

    @Test
    void 모든_상품_수량을_반환한다() {
        // given
        paymentProductList.addPaymentProduct(new PaymentProduct("콜라", 2, 1000, 0));
        paymentProductList.addPaymentProduct(new PaymentProduct("사이다", 3, 1500, 0));

        // when
        List<Integer> quantities = paymentProductList.getAllProductQuantities();

        // then
        assertThat(quantities).containsExactly(2, 3);
    }
}