package store.view;

import java.util.List;

public class OutputView {

    private static final String WELCOME_MESSAGE = "안녕하세요. W편의점입니다.";
    private static final String PRODUCT_INFO_MESSAGE = "현재 보유하고 있는 상품입니다.";
    private static final String PRODUCT_FORMAT = "- %s %s원 %s개 %s";
    private static final String NEW_LINE = System.lineSeparator();

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println(PRODUCT_INFO_MESSAGE + NEW_LINE);
    }

    public void printProductsInfo(List<List<String>> products) {
        products.forEach(this::printProduct);
    }

    public void printProduct(List<String> product) {
        System.out.printf(PRODUCT_FORMAT + NEW_LINE, product.toArray());
    }
}
