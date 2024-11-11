package store.global.constant;

public enum MessageConstant {
    OUTPUT_WELCOME("안녕하세요. W편의점입니다."),
    OUTPUT_PRODUCT_INFO("현재 보유하고 있는 상품입니다."),
    OUTPUT_PRODUCT_PREFIX("- "),
    OUTPUT_PROMOTION_NOT_APPLIED("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)"),
    OUTPUT_PROMOTION_ADD("현재 %s은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)"),

    OUTPUT_HEADER_STORE("==============W 편의점================"),
    OUTPUT_HEADER_PROMOTION("=============증      정==============="),
    OUTPUT_HEADER_SEPARATOR("===================================="),

    FORMAT("%-18s %-8s %5s"),
    NEW_LINE(System.lineSeparator());

    private final String message;

    MessageConstant(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}