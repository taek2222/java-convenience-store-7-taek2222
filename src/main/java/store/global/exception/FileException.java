package store.global.exception;

import store.global.constant.ErrorMessage;

public class FileException extends RuntimeException {
    public FileException(ErrorMessage error, String fileName) {
        super(String.format(error.getMessage(), fileName));
    }

    public FileException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
