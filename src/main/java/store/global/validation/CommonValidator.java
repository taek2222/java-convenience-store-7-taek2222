package store.global.validation;

import store.global.constant.ErrorMessage;

public class CommonValidator {

    public static void validateBlank(final String input, final ErrorMessage error) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }

    public static void validateNotNumeric(final String input, final ErrorMessage error) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }
}
