package store.domain;

import java.util.List;
import store.global.constant.ErrorMessage;

public class Products {
    List<Product> products;

    public Products(List<Product> products) {
        this.products = products;
    }

    public List<String> getAllProductsInfo() {
        return products.stream()
                .map(Product::toString)
                .toList();
    }

    public List<Product> findProductByEqualsName(String name) {
        List<Product> list = products.stream()
                .filter(product -> product.hasSameName(name))
                .toList();
        validateProductListNotEmpty(list);
        return list;
    }

    private void validateProductListNotEmpty(List<Product> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.PRODUCT_NOT_FOUND.getMessage());
        }
    }
}
