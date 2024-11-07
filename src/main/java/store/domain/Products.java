package store.domain;

import java.util.ArrayList;
import java.util.List;

public class Products {
    List<Product> products;

    public Products() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<List<String>> getAllProductsInfo() {
        return products.stream()
                .map(Product::buildInfo)
                .toList();
    }
}
