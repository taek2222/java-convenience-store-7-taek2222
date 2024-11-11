package store.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class PaymentProductTest {

    @Test
    void 프로모션_여부를_확인한다() {
        // given
        PaymentProduct paymentProductWithPromotion = new PaymentProduct("콜라", 2, 1000, 1);
        PaymentProduct paymentProductWithoutPromotion = new PaymentProduct("콜라", 2, 1000, 0);

        // when & then
        assertThat(paymentProductWithPromotion.isPromotion()).isTrue();
        assertThat(paymentProductWithoutPromotion.isPromotion()).isFalse();
    }

    @Test
    void 상품_수량을_반환한다() {
        // given
        PaymentProduct paymentProduct = new PaymentProduct("콜라", 2, 1000, 0);

        // when
        int quantity = paymentProduct.getQuantity();

        // then
        assertThat(quantity).isEqualTo(2);
    }

    @Test
    void 총_가격을_계산한다() {
        // given
        PaymentProduct paymentProduct = new PaymentProduct("콜라", 2, 1000, 0);

        // when
        int totalPrice = paymentProduct.getTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(2000);
    }

    @Test
    void 프로모션_가격을_계산한다() {
        // given
        PaymentProduct paymentProduct = new PaymentProduct("콜라", 2, 1000, 1);

        // when
        int promotionPrice = paymentProduct.getPromotionPrice();

        // then
        assertThat(promotionPrice).isEqualTo(1000);
    }
}
