package store.view;

import static store.view.InputValidator.validateNonEmptyFileContents;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import store.global.util.FileUtil;

public class InputView {
    private static final String PURCHASE_MESSAGE = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";
    private static final String MEMBERSHIP_MESSAGE = "멤버십 할인을 받으시겠습니까? (Y/N)";
    private static final String ADDITIONAL_PURCHASE_MESSAGE = "감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)";
    private static final String PRODUCTS_FILE_NAME = "products.md";
    private static final String PROMOTIONS_FILE_NAME = "promotions.md";
    private static final int COLUMN_LINE = 1;
    private static final String NEW_LINE = System.lineSeparator();

    private final FileUtil fileUtil;

    public InputView(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    public String readPurchaseInput() {
        System.out.println(NEW_LINE + PURCHASE_MESSAGE);
        return Console.readLine();
    }

    public List<List<String>> readPromotionsData() {
        return readFileData(PROMOTIONS_FILE_NAME);
    }

    public List<List<String>> readProductsData() {
        return readFileData(PRODUCTS_FILE_NAME);
    }

    public boolean readMembershipConfirmation() {
        System.out.println(NEW_LINE + MEMBERSHIP_MESSAGE);
        return readYesOrNoInput();
    }

    public boolean readAdditionalPurchaseConfirmation() {
        System.out.println(NEW_LINE + ADDITIONAL_PURCHASE_MESSAGE);
        return readYesOrNoInput();
    }

    public boolean readYesOrNoInput() {
        String input = Console.readLine().toUpperCase();
        InputValidator.validateYesOrNoInput(input);
        return input.equals("Y");
    }

    private List<List<String>> readFileData(String fileName) {
        List<List<String>> fileContents = fileUtil.readFile(fileName);
        validateNonEmptyFileContents(fileContents);
        return fileContents.subList(COLUMN_LINE, fileContents.size()); // 파일 첫 줄의 컬럼을 제외하고 반환
    }
}
