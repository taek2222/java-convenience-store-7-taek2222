package store.constant;

public enum ErrorMessage {
    INVALID_NAME_NOT_NULL("물품 이름은 NULL 일 수 없습니다."),
    INVALID_NAME_BLANK("물품 이름은 공백 및 빈칸일 수 없습니다."),
    INVALID_PRICE_TOO_LOW("최소 금액 %d원 이상의 물품만 등록할 수 있습니다."),

    FILE_NOT_FOUND("%s 파일을 찾을 수 없습니다. 파일 이름을 확인해 주세요."),
    FILE_CONTAINS_BLANK_CONTENT("파일의 내용 중 공백인 부분이 있습니다. 파일을 확인해 주세요."),
    FILE_CONTENT_NULL("파일의 내용이 NULL 입니다. 파일을 확인해 주세요."),
    FILE_CONTENT_INSUFFICIENT("파일의 정보가 부족합니다. 파일을 확인해 주세요.");

    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}
