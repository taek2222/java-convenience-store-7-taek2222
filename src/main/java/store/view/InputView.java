package store.view;

import static store.view.InputValidator.validateNonEmptyFileContents;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class InputView {
    private static final String PURCHASE_MESSAGE = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";
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

    public List<List<String>> readProductsFile() {
        List<List<String>> inputProducts = fileInputView.readFile(PRODUCTS_FILE_NAME);
        validateNonEmptyFileContents(inputProducts);

        return inputProducts.subList(COLUMN_LINE, inputProducts.size()); // 파일 첫 줄의 컬럼을 제외하고 반환
    }

    public List<List<String>> readPromotionsFile() {
        List<List<String>> lists = fileInputView.readFile(PROMOTIONS_FILE_NAME);
        return lists.subList(COLUMN_LINE, lists.size());
    }
}
