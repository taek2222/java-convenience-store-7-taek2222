package store.domain;

import static store.global.constant.MessageConstant.FORMAT;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PaymentProductList {

    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("###,###");

    private List<PaymentProduct> paymentProducts;
    private int totalPrice = 0;
    private int totalPromotion = 0;
    private int membershipPrice = 0;

    public PaymentProductList() {
        this.paymentProducts = new ArrayList<>();
    }

    public void addPaymentProduct(PaymentProduct product) {
        paymentProducts.add(product);
    }

    public List<Integer> getAllProductQuantities() {
        return paymentProducts.stream()
                .map(PaymentProduct::getQuantity)
                .toList();
    }

    public List<String> infoProduct() {
        return paymentProducts.stream()
                .map(PaymentProduct::toString)
                .toList();
    }

    public List<String> infoPromotion() {
        return paymentProducts.stream()
                .filter(PaymentProduct::isPromotion)
                .map(PaymentProduct::buildPromotion)
                .toList();
    }

    public List<String> finalResult() {
        List<String> result = new ArrayList<>();
        result.add(String.format(FORMAT.getMessage(), "총구매액", totalQuantity(), totalPrice()));
        result.add(String.format(FORMAT.getMessage(), "행사할인", "", totalPromotionPrice()));
        result.add(String.format(FORMAT.getMessage(), "멤버십할인", "", "-" + PRICE_FORMAT.format(membershipPrice)));
        result.add(String.format(FORMAT.getMessage(), "내실돈", "",
                PRICE_FORMAT.format(totalPrice - totalPromotion - membershipPrice)));

        return result;
    }

    private int totalQuantity() {
        return paymentProducts.stream()
                .mapToInt(PaymentProduct::getQuantity)
                .sum();
    }

    private String totalPrice() {
        this.totalPrice = paymentProducts.stream()
                .mapToInt(PaymentProduct::getTotalPrice)
                .sum();
        return PRICE_FORMAT.format(totalPrice);
    }

    private String totalPromotionPrice() {
        this.totalPromotion = paymentProducts.stream()
                .filter(PaymentProduct::isPromotion)
                .mapToInt(PaymentProduct::getPromotionPrice)
                .sum();
        return "-" + PRICE_FORMAT.format(totalPromotion);
    }

    public void applyMembershipDiscount() {
        int price = (int) (paymentProducts.stream()
                .filter(product -> !product.isPromotion())
                .mapToInt(PaymentProduct::getTotalPrice)
                .sum() * 0.3);

        if (price > 8000) {
            price = 8000;
        }

        this.membershipPrice = price;
    }
}
