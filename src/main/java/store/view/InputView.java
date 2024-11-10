package store.view;

import static store.view.InputValidator.validateNonEmptyFileContents;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class InputView {
    private static final String PURCHASE_MESSAGE = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";
    private static final String PROMOTION_NOT_APPLIED_MESSAGE = "현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)";
    private static final String PROMOTION_ADD_MESSAGE = "현재 %s은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)";
    private static final String PRODUCTS_FILE_NAME = "products.md";
    private static final String PROMOTIONS_FILE_NAME = "promotions.md";
    private static final int COLUMN_LINE = 1;
    private static final String NEW_LINE = System.lineSeparator();

    private final FileInputView fileInputView;

    public InputView(FileInputView fileInputView) {
        this.fileInputView = fileInputView;
    }

    public String readPurchaseProduct() {
        System.out.println(NEW_LINE + PURCHASE_MESSAGE);
        return Console.readLine();
    }

    public String readPurchaseWithoutPromotion(String name, int quantity) {
        return readYesOrNo(String.format(PROMOTION_NOT_APPLIED_MESSAGE, name, quantity));
    }

    public String readPromotionAddition(String name) {
        return readYesOrNo(String.format(PROMOTION_ADD_MESSAGE, name));
    }

    public List<List<String>> readPromotionsFile() {
        List<List<String>> lists = fileInputView.readFile(PROMOTIONS_FILE_NAME);
        return lists.subList(COLUMN_LINE, lists.size());
    }

    public List<List<String>> readProductsFile() {
        List<List<String>> inputProducts = fileInputView.readFile(PRODUCTS_FILE_NAME);
        validateNonEmptyFileContents(inputProducts);

        return inputProducts.subList(COLUMN_LINE, inputProducts.size()); // 파일 첫 줄의 컬럼을 제외하고 반환
    }

    private String readYesOrNo(String message) {
        System.out.println(NEW_LINE + message);
        String readLine = Console.readLine().toUpperCase();
        InputValidator.validateYesOrNoInput(readLine);

        return readLine;
    }
}
