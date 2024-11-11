package store.view;

import static store.global.constant.MessageConstant.FORMAT;
import static store.global.constant.MessageConstant.NEW_LINE;
import static store.global.constant.MessageConstant.OUTPUT_HEADER_PROMOTION;
import static store.global.constant.MessageConstant.OUTPUT_HEADER_SEPARATOR;
import static store.global.constant.MessageConstant.OUTPUT_HEADER_STORE;
import static store.global.constant.MessageConstant.OUTPUT_PRODUCT_INFO;
import static store.global.constant.MessageConstant.OUTPUT_PRODUCT_PREFIX;
import static store.global.constant.MessageConstant.OUTPUT_PROMOTION_ADD;
import static store.global.constant.MessageConstant.OUTPUT_PROMOTION_NOT_APPLIED;
import static store.global.constant.MessageConstant.OUTPUT_WELCOME;

import java.util.List;

public class OutputView {

    public void printWelcomeMessage() {
        System.out.println(OUTPUT_WELCOME.getMessage());
        System.out.println(OUTPUT_PRODUCT_INFO.getMessage() + NEW_LINE.getMessage());
    }

    public void printProductsInfo(List<String> products) {
        products.forEach(product ->
                System.out.println(OUTPUT_PRODUCT_PREFIX.getMessage() + product)
        );
    }

    public void printPromotionNotApplied(String name, int quantity) {
        System.out.printf(NEW_LINE.getMessage() + OUTPUT_PROMOTION_NOT_APPLIED.getMessage() + NEW_LINE.getMessage(), name, quantity);
    }

    public void printPromotionAddition(String name) {
        System.out.printf(NEW_LINE.getMessage() + OUTPUT_PROMOTION_ADD.getMessage() + NEW_LINE.getMessage(), name);
    }

    public void printPaymentInfoResult(List<String> infos) {
        System.out.println(OUTPUT_HEADER_STORE.getMessage());
        System.out.printf(FORMAT.getMessage() + NEW_LINE.getMessage(), "상품명", "수량", "금액");
        infos.forEach(System.out::println);
    }

    public void printPromotionInfoResult(List<String> strings) {
        System.out.println(OUTPUT_HEADER_PROMOTION.getMessage());
        strings.forEach(System.out::println);
    }

    public void printFinalResult(List<String> result) {
        System.out.println(OUTPUT_HEADER_SEPARATOR.getMessage());
        result.forEach(System.out::println);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
