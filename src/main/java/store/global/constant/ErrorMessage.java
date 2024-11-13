package store.global.constant;

public enum ErrorMessage {
    INVALID_INPUT_PURCHASE("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요."),
    INVALID_INPUT("잘못된 입력입니다. 다시 입력해 주세요."),

    INVALID_PRICE_NUMERIC("물품 가격은 숫자만 가능합니다. 다시 확인해 주세요."),
    INVALID_PRICE_OUT_OF_RANGE("물품 가격은 범위내만 등록할 수 있습니다. 다시 확인해 주세요."),

    INSUFFICIENT_STOCK("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요."),
    INVALID_QUANTITY_NUMERIC("물품 수량은 숫자만 가능합니다. 다시 확인해 주세요."),
    INVALID_QUANTITY_OUT_OF_RANGE("물품 수량은 범위내만 등록할 수 있습니다. 다시 확인해 주세요."),

    PRODUCT_NOT_FOUND("존재하지 않는 상품입니다. 다시 입력해 주세요."),
    INVALID_PRODUCT_ELEMENT("상품 정보의 요소가 올바르지 않았습니다. 파일을 확인해 주세요."),

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
