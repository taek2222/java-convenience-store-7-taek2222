package store.global.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import store.global.constant.ErrorMessage;
import store.global.validation.CommonValidator;

public class ProductParser {

    public static Map<String, Integer> parseInputProduct(String input) {
        Pattern pattern = Pattern.compile("^\\[(.*?)-(\\d+)]$");
        Map<String, Integer> product = new HashMap<>();

        Pattern compile = Pattern.compile("^\\[.*]$");
        Matcher matcher1 = compile.matcher(input);
        if (!matcher1.find())
            throw new IllegalArgumentException();

        String[] split = input.split(",");

        for (String s : split) {
            Matcher matcher = pattern.matcher(s);
            System.out.println(s);

            if (!matcher.find() || matcher.groupCount() != 2) {
                throw new IllegalArgumentException();
            }

            String name = matcher.group(1);

            Pattern compile12 = Pattern.compile("[-\\[\\]]");
            Matcher matcher12 = compile12.matcher(name);

            if (matcher12.find())
                throw new IllegalArgumentException();
            CommonValidator.validateBlank(name, ErrorMessage.INVALID_PRODUCT_ELEMENT);

            int quantity = Integer.parseInt(matcher.group(2));

            System.out.println(name + " " + quantity);
            product.merge(name, quantity, Integer::sum);
        }
        return product;
    }
}
