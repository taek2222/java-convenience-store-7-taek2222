package store.view;

import java.util.List;

public class InputValidator {

    public static void validateFileContents(List<List<String>> input) {
        if (input == null || input.size() == 1) {
            throw new IllegalArgumentException();
        }
    }
}
