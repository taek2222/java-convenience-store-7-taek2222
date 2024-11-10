package store.controller;

import java.util.List;
import java.util.Map;
import store.domain.Product;
import store.domain.Products;
import store.domain.Promotions;
import store.domain.PromotionsList;
import store.domain.PurchaseList;
import store.domain.PurchaseProduct;
import store.global.util.ProductParser;
import store.global.util.PromotionParser;
import store.global.util.PurchaseParser;
import store.view.InputView;
import store.view.OutputView;

public class StoreController {
    private final OutputView outputView;
    private final InputView inputView;

    public StoreController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        displayWelcomeMessage();

        List<List<String>> inputPromotions = inputView.readPromotionsFile();
        List<Promotions> promotionsList1 = PromotionParser.parseToPromotions(inputPromotions);
        PromotionsList promotionsList = new PromotionsList(promotionsList1);

        Products products = loadProducts(promotionsList);

        displayProductsInfo(products);
        handlePurchases(products);
    }

    private void displayWelcomeMessage() {
        outputView.printWelcomeMessage();
    }

    private Products loadProducts(PromotionsList promotionsList) {
        List<List<String>> inputProducts = inputView.readProductsFile();
        List<Product> productList = ProductParser.parseToProducts(inputProducts, promotionsList);
        return new Products(productList);
    }

    private void displayProductsInfo(Products products) {
        outputView.printProductsInfo(products.getAllProductsInfo());
    }

    private void handlePurchases(Products products) {
        while (true) {
            try {
                Map<String, Integer> purchases = readAndParsePurchaseInput();
                processPurchases(products, purchases);
                break;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private Map<String, Integer> readAndParsePurchaseInput() {
        String input = inputView.readPurchaseProduct();
        return PurchaseParser.parseInputProduct(input);
    }

    private void processPurchases(Products products, Map<String, Integer> purchases) {
        PurchaseList purchaseList = new PurchaseList();

        for (String productName : purchases.keySet()) {
            List<Product> foundProducts = products.findProductByEqualsName(productName);
            PurchaseProduct purchaseProduct = new PurchaseProduct(foundProducts);
            purchaseProduct.validateSufficientStock(purchases.get(productName));

            purchaseList.addPurchase(purchaseProduct);
        }
    }
}
