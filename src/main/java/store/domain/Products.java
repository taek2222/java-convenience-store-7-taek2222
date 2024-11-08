package store.domain;

import java.util.List;

public class Products {
    List<Product> products;

    public Products(List<List<String>> products) {
        this.products = createProducts(products);
    }

    public List<List<String>> getAllProductsInfo() {
        return products.stream()
                .map(Product::buildInfo)
                .toList();
    }

    private List<Product> createProducts(List<List<String>> products) {
        return products.stream()
                .map(Product::new)
                .toList();
    }
}
