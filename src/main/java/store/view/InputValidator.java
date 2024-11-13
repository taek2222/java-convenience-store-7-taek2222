package store.view;

import static store.global.constant.ErrorMessage.FILE_CONTENT_INSUFFICIENT;
import static store.global.constant.ErrorMessage.FILE_CONTENT_NULL;
import static store.global.constant.ErrorMessage.INVALID_INPUT;

import java.util.List;
import store.global.exception.FileException;

public class InputValidator {

    private static final int MINIMUM_VALID_LINES = 1;

    public static void validateNonEmptyFileContents(List<List<String>> input) {
        if (input == null) {
            throw new FileException(FILE_CONTENT_NULL);
        }
        if (input.size() <= MINIMUM_VALID_LINES) {
            throw new FileException(FILE_CONTENT_INSUFFICIENT);
        }
    }

    public static void validateYesOrNoInput(String input) {
        if (!input.equals("Y") && !input.equals("N")) {
            throw new IllegalArgumentException(INVALID_INPUT.getMessage());
        }
    }
}
