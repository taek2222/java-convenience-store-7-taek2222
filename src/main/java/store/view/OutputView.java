package store.view;

import java.util.List;

public class OutputView {

    private static final String WELCOME_MESSAGE = "안녕하세요. W편의점입니다.";
    private static final String PRODUCT_INFO_MESSAGE = "현재 보유하고 있는 상품입니다.";
    private static final String PRODUCT_PREFIX = "- ";
    private static final String NEW_LINE = System.lineSeparator();

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println(PRODUCT_INFO_MESSAGE + NEW_LINE);
    }

    public void printProductsInfo(List<String> products) {
        products.forEach(product -> System.out.println(PRODUCT_PREFIX + product));
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
