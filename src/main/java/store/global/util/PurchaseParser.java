package store.global.util;

import static store.global.constant.ErrorMessage.INVALID_INPUT_PURCHASE;
import static store.global.validation.CommonValidator.validateBlank;
import static store.global.validation.CommonValidator.validateNotNumeric;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PurchaseParser {

    private static final Pattern PRODUCT_PATTERN = Pattern.compile("^\\[(.*?)-(\\d+)]$");
    private static final Pattern INVALID_NAME_PATTERN = Pattern.compile(".*[-\\[\\]].*");

    public static Map<String, Integer> parseInputProduct(String input) {
        validateInput(input);

        String[] splitProducts = splitInput(input);
        LinkedHashMap<String, Integer> product = new LinkedHashMap<>();

        for (String productString : splitProducts) {
            processProductString(productString, product);
        }
        return product;
    }

    private static String[] splitInput(String input) {
        return input.split(",");
    }

    private static void processProductString(String productString, Map<String, Integer> product) {
        String name = extractProductName(productString);
        String quantity = extractProductQuantity(productString);
        addProductToMap(name, quantity, product);
    }

    private static String extractProductName(String productString) {
        Matcher matcher = matchProductPattern(productString);
        String name = matcher.group(1);
        validateName(name);
        return name;
    }

    private static String extractProductQuantity(String productString) {
        Matcher matcher = matchProductPattern(productString);
        String quantity = matcher.group(2);
        validateNotNumeric(quantity, INVALID_INPUT_PURCHASE);
        return quantity;
    }

    private static Matcher matchProductPattern(String productString) {
        Matcher matcher = PRODUCT_PATTERN.matcher(productString);
        if (!matcher.find()) {
            throw new IllegalArgumentException(INVALID_INPUT_PURCHASE.getMessage());
        }
        return matcher;
    }

    private static void addProductToMap(String name, String quantity, Map<String, Integer> product) {
        product.merge(name, Integer.parseInt(quantity), Integer::sum);
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
