package store.global.util;

import static store.global.constant.ErrorMessage.INVALID_INPUT_PURCHASE;
import static store.global.validation.CommonValidator.validateBlank;
import static store.global.validation.CommonValidator.validateNotNumeric;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PurchaseParser {

    private static final Pattern PRODUCT_PATTERN = Pattern.compile("^\\[(.*?)-(\\d+)]$");
    private static final Pattern INVALID_NAME_PATTERN = Pattern.compile(".*[-\\[\\]].*");

    public static Map<String, Integer> parseInputProduct(String input) {
        validateInput(input);

        String[] split = input.split(",");
        Map<String, Integer> product = new HashMap<>();

        for (String s : split) {
            Matcher matcher = PRODUCT_PATTERN.matcher(s);

            if (!matcher.find()) {
                throw new IllegalArgumentException(INVALID_INPUT_PURCHASE.getMessage());
            }

            String name = matcher.group(1);
            validateName(name);

            String quantity = matcher.group(2);
            validateNotNumeric(quantity, INVALID_INPUT_PURCHASE);

            product.merge(name, Integer.parseInt(quantity), Integer::sum);
        }
        return product;
    }

    private static void validateName(String name) {
        validateBlank(name, INVALID_INPUT_PURCHASE);
        if (INVALID_NAME_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(INVALID_INPUT_PURCHASE.getMessage());
        }
    }

    private static void validateInput(String input) {
        if (!input.matches("^\\[.*]$")) {
            throw new IllegalArgumentException(INVALID_INPUT_PURCHASE.getMessage());
        }
    }
}
