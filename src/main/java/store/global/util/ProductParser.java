package store.global.util;

import java.util.ArrayList;
import java.util.List;
import store.domain.Product;
import store.domain.PromotionsList;
import store.domain.product.Name;
import store.domain.product.Price;
import store.domain.product.Quantity;

public class ProductParser {
    private static final String DEFAULT_QUANTITY = "0";
    private static final String NO_PROMOTION = "null";

    private static PromotionsList promotionsList;

    public static List<Product> parseToProducts(final List<List<String>> inputData, final PromotionsList promotionsList) {
        ProductParser.promotionsList = promotionsList;

        List<Product> products = new ArrayList<>();
        for (List<String> productData : inputData) {
            Product product = createProduct(productData);
            products.add(product);
            addDefaultProductIfNecessary(products, inputData, productData, product);
        }
        return products;
    }

    private static void addDefaultProductIfNecessary(List<Product> products, List<List<String>> inputData,
                                                     List<String> productData, Product product) {
        if (product.hasPromotion() && isSingleProductInInput(inputData, productData)) {
            Product defaultProduct = createDefaultProduct(productData);
            products.add(defaultProduct);
        }
    }

    private static boolean isSingleProductInInput(List<List<String>> inputData, List<String> productData) {
        return inputData.stream()
                .filter(info -> info.contains(productData.get(0)))
                .count() == 1;
    }

    private static Product createDefaultProduct(List<String> productData) {
        return createProduct(List.of(
                productData.get(0),
                productData.get(1),
                DEFAULT_QUANTITY,
                NO_PROMOTION
        ));
    }

    private static Product createProduct(List<String> productData) {
        return new Product(
                new Name(productData.get(0)),
                new Price(productData.get(1)),
                new Quantity(productData.get(2)),
                promotionsList.findPromotion(productData.get(3))
        );
    }
}
