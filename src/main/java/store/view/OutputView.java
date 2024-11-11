package store.view;

import static store.global.constant.Message.FORMAT;

import java.util.List;

public class OutputView {

    private static final String WELCOME_MESSAGE = "안녕하세요. W편의점입니다.";
    private static final String PRODUCT_INFO_MESSAGE = "현재 보유하고 있는 상품입니다.";
    private static final String PROMOTION_NOT_APPLIED_MESSAGE = "현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)";
    private static final String PROMOTION_ADD_MESSAGE = "현재 %s은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)";
    private static final String HEADER = "==============W 편의점================";
    private static final String PROMOTION = "=============증      정===============";

    private static final String PRODUCT_PREFIX = "- ";
    private static final String NEW_LINE = System.lineSeparator();

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println(PRODUCT_INFO_MESSAGE + NEW_LINE);
    }

    public void printProductsInfo(List<String> products) {
        products.forEach(product ->
                System.out.println(PRODUCT_PREFIX + product)
        );
    }

    public void printPromotionNotApplied(String name, int quantity) {
        System.out.printf(NEW_LINE);
        System.out.printf(PROMOTION_NOT_APPLIED_MESSAGE, name, quantity);
        System.out.println();
    }

    public void printPromotionAddition(String name) {
        System.out.printf(NEW_LINE);
        System.out.printf(PROMOTION_ADD_MESSAGE, name);
        System.out.println();
    }

    public void printPaymentInfoResult(List<String> infos) {
        System.out.println(HEADER);
        System.out.printf(FORMAT + NEW_LINE, "상품명", "수량", "금액");
        infos.forEach(System.out::println);
    }

    public void printPromotionInfoResult(List<String> strings) {
        System.out.println(PROMOTION);
        strings.forEach(System.out::println);
    }

    public void printFinalResult(List<String> result) {
        System.out.println("====================================");
        result.forEach(System.out::println);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
