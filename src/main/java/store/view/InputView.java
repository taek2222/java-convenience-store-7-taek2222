package store.view;

import static store.view.InputValidator.*;

import java.util.List;

public class InputView {
    private static final String PRODUCTS_FILE_NAME = "products.md";
    private static final int COLUMN_LINE = 1;

    private final FileInputView fileInputView;

    public InputView(FileInputView fileInputView) {
        this.fileInputView = fileInputView;
    }

    public List<List<String>> readProductsFile() {
        List<List<String>> inputProducts = fileInputView.readFile(PRODUCTS_FILE_NAME);
        validateNonEmptyFileContents(inputProducts);

        return inputProducts.subList(COLUMN_LINE, inputProducts.size()); // 파일 첫 줄의 컬럼을 제외하고 반환
    }
}
