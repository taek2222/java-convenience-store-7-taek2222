package store.controller;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import store.domain.PaymentProductList;
import store.domain.Product;
import store.domain.Products;
import store.domain.Promotions;
import store.domain.PromotionsList;
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

        PromotionsList promotionsList = loadPromotions();
        Products products = loadProducts(promotionsList);

        displayProductsInfo(products);
        List<PurchaseProduct> purchases = handlePurchases(products);

        PaymentProductList paymentProductList = new PaymentProductList();
        LocalDateTime now = DateTimes.now();

        for (PurchaseProduct purchase : purchases) {
            if (purchase.hasPromotionProduct() && purchase.nodatelate(now)) {
                int calculate = purchase.calculate();
                int remainingStock = purchase.calculateRemainingStock();
                if (remainingStock > 0) {
                    int promotionRate = purchase.calculatePromotionRate(Math.abs(remainingStock));
                    int quantity = remainingStock + promotionRate;
                    outputView.printPromotionNotApplied(purchase.getProductName(),
                            quantity);
                    boolean answer = inputView.readYesOrNo();

                    if (!answer) {
                        purchase.decrease(quantity);
                    }

                    paymentProductList.addPaymentProduct(purchase.createPaymentProduct(0));
                    continue;
                }

                if (remainingStock != 0) {
                    outputView.printPromotionAddition(purchase.getProductName());
                    boolean answer = inputView.readYesOrNo();
                    purchase.calculatePromotionRate(0);
                    int calculate1 = purchase.calculate();
                    if (answer) {
                        calculate1 += 1;
                    }
                    paymentProductList.addPaymentProduct(purchase.createPaymentProduct(calculate1));
                    continue;
                }
                paymentProductList.addPaymentProduct(purchase.createPaymentProduct(calculate));
                continue;
            }
            paymentProductList.addPaymentProduct(purchase.createPaymentProduct(0));
        }

        List<String> infos = paymentProductList.infoProduct();
        List<String> strings = paymentProductList.infoPromotion();
        boolean answer = inputView.readMemberShip();
        if (answer) {
            paymentProductList.membership();
        }
        List<String> result = paymentProductList.finalResult();

        outputView.printPaymentInfoResult(infos);
        outputView.printPromotionInfoResult(strings);
        outputView.printFinalResult(result);
    }

    private void displayWelcomeMessage() {
        outputView.printWelcomeMessage();
    }

    private PromotionsList loadPromotions() {
        List<List<String>> inputPromotions = inputView.readPromotionsFile();
        List<Promotions> promotionsList1 = PromotionParser.parseToPromotions(inputPromotions);
        return new PromotionsList(promotionsList1);
    }

    private Products loadProducts(PromotionsList promotionsList) {
        List<List<String>> inputProducts = inputView.readProductsFile();
        List<Product> productList = ProductParser.parseToProducts(inputProducts, promotionsList);
        return new Products(productList);
    }

    private void displayProductsInfo(Products products) {
        outputView.printProductsInfo(products.getAllProductsInfo());
    }

    private List<PurchaseProduct> handlePurchases(Products products) {
        while (true) {
            try {
                Map<String, Integer> purchases = readAndParsePurchaseInput();
                return processPurchases(products, purchases);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private Map<String, Integer> readAndParsePurchaseInput() {
        String input = inputView.readPurchaseProduct();
        return PurchaseParser.parseInputProduct(input);
    }

    private List<PurchaseProduct> processPurchases(Products products, Map<String, Integer> purchases) {
        LinkedList<PurchaseProduct> purchaseProducts = new LinkedList<>();
        for (String productName : purchases.keySet()) {
            List<Product> foundProducts = products.findProductByEqualsName(productName);
            PurchaseProduct purchaseProduct = new PurchaseProduct(foundProducts, purchases.get(productName));
            purchaseProducts.add(purchaseProduct);
        }
        return purchaseProducts;
    }
}
