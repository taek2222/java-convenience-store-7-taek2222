package store.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Products {
    List<Product> products;

    public Products(List<List<String>> products) {
        this.products = createProducts(products);
    }

    public List<String> getAllProductsInfo() {
        return products.stream()
                .map(Product::buildInfo)
                .toList();
    }

    private List<Product> createProducts(List<List<String>> productsData) {
        List<Product> productList = new ArrayList<>();
        for (List<String> productData : productsData) {
            Product product = new Product(productData);
            productList.add(product);
            addProductWithDefault(productsData, productList, productData);
        }
        return productList;
    }

    private void addProductWithDefault(List<List<String>> productsData, List<Product> productList, List<String> productData) {
        if (!isNotNull(productData)) {
            return;
        }
        Optional<List<String>> optional = findOptionalProduct(productsData, productData);
        if (optional.isEmpty()) {
            productList.add(createDefaultProduct(productData));
        }
    }

    private boolean isNotNull(List<String> productData) {
        return !productData.get(3).equals("null");
    }

    private Optional<List<String>> findOptionalProduct(List<List<String>> productsData, List<String> productData) {
        return productsData.stream()
                .filter(e -> e.getFirst().equals(productData.getFirst()))
                .filter(e -> e.get(3).equals("null"))
                .findFirst();
    }

    private Product createDefaultProduct(List<String> productData) {
        return new Product(List.of(productData.get(0), productData.get(1), "0", "null"));
    }

    public List<Product> findProductByEqualsName(String name) {
        return products.stream()
                .map(product -> product.findEqualsName(name))
                .toList();
    }
}
