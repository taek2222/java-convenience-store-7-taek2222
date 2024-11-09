package store.global.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductParser {

    public static Map<String, Integer> parseInputProduct(String input) {
        Pattern pattern = Pattern.compile("\\[(.*?)-(\\d+)]");
        Map<String, Integer> product = new HashMap<>();

        String[] split = input.split(",");
        for (String s : split) {
            Matcher matcher = pattern.matcher(s);
            if (matcher.find()) {
                String name = matcher.group(1);
                int quantity = Integer.parseInt(matcher.group(2));
                product.merge(name, quantity, Integer::sum);
            }
        }
        return product;
    }
}
