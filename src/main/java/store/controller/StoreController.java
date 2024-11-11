package store.controller;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        Products products = loadStoreFiles();

        do {
            displayWelcome(products);
            List<PurchaseProduct> purchases = handlePurchases(products);
            PaymentProductList paymentProductList = processPurchases(purchases);

            displayPaymentInfo(paymentProductList);
        } while (readAdditionalPurchaseConfirmationWithRetry());
    }

    private void displayWelcome(Products products) {
        displayWelcomeMessage();
        displayProductsInfo(products);
    }

    private Products loadStoreFiles() {
        PromotionsList promotionsList = loadPromotions();
        return loadProducts(promotionsList);
    }

    private PaymentProductList processPurchases(List<PurchaseProduct> purchases) {
        PaymentProductList paymentProductList = new PaymentProductList();
        LocalDateTime now = DateTimes.now();

        for (PurchaseProduct purchase : purchases) {
            processSinglePurchase(paymentProductList, purchase, now);
        }

        decreaseProductStock(purchases, paymentProductList);
        applyMembershipDiscountIfConfirmed(paymentProductList);
        return paymentProductList;
    }

    private void decreaseProductStock(List<PurchaseProduct> purchases, PaymentProductList paymentProductList) {
        List<Integer> quantities = paymentProductList.getAllProductQuantities();
        for (int i = 0; i < purchases.size(); i++) {
            purchases.get(i).reduceProductStock(quantities.get(i));
        }
    }

    private void processSinglePurchase(PaymentProductList paymentProductList, PurchaseProduct purchase,
                                       LocalDateTime now) {
        if (purchase.hasPromotionProduct() && purchase.isWithinPromotionPeriod(now)) {
            handlePromotionProduct(paymentProductList, purchase);
            return;
        }
        paymentProductList.addPaymentProduct(purchase.createPaymentProduct(0));
    }


    private void handlePromotionProduct(PaymentProductList paymentProductList, PurchaseProduct purchase) {
        int remainingStock = purchase.getRemainingStock();
        if (remainingStock > 0) {
            handleRemainingStock(paymentProductList, purchase, remainingStock);
            return;
        }
        if (remainingStock < 0 && purchase.isPromotionAdditionalProduct()) {
            handleNonZeroRemainingStock(paymentProductList, purchase);
            return;
        }
        int calculate = purchase.getPromotionUnits();
        paymentProductList.addPaymentProduct(purchase.createPaymentProduct(calculate));
    }

    private void handleRemainingStock(PaymentProductList paymentProductList, PurchaseProduct purchase,
                                      int remainingStock) {
        int quantity = calculateQuantity(purchase, remainingStock);

        if (!confirmPromotionNotApplied(purchase.getProductName(), quantity)) {
            purchase.decrease(quantity);
        }
        int calculate = purchase.getPromotionUnits();
        paymentProductList.addPaymentProduct(purchase.createPaymentProduct(calculate - 1));
    }

    private int calculateQuantity(PurchaseProduct purchase, int remainingStock) {
        int promotionRate = purchase.getPromotionRate(Math.abs(remainingStock));
        return remainingStock + promotionRate;
    }

    private void handleNonZeroRemainingStock(PaymentProductList paymentProductList, PurchaseProduct purchase) {
        boolean answer = confirmPromotionAddition(purchase.getProductName());
        int calculate = purchase.getPromotionUnits();
        if (answer) {
            purchase.increaseQuantityForPromotion();
            calculate += 1;
        }
        paymentProductList.addPaymentProduct(purchase.createPaymentProduct(calculate));
    }

    private void displayPaymentInfo(PaymentProductList paymentProductList) {
        List<String> productInfos = paymentProductList.infoProduct();
        List<String> promotionInfos = paymentProductList.infoPromotion();
        List<String> finalResults = paymentProductList.finalResult();

        outputView.printPaymentInfoResult(productInfos);
        outputView.printPromotionInfoResult(promotionInfos);
        outputView.printFinalResult(finalResults);
    }

    private void applyMembershipDiscountIfConfirmed(PaymentProductList paymentProductList) {
        if (readMembershipConfirmationWithRetry()) {
            paymentProductList.applyMembershipDiscount();
        }
    }

    private void displayWelcomeMessage() {
        outputView.printWelcomeMessage();
    }

    private PromotionsList loadPromotions() {
        List<List<String>> inputPromotions = inputView.readPromotionsData();
        List<Promotions> promotionsList1 = PromotionParser.parseToPromotions(inputPromotions);
        return new PromotionsList(promotionsList1);
    }

    private Products loadProducts(PromotionsList promotionsList) {
        List<List<String>> inputProducts = inputView.readProductsData();
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
        String input = inputView.readPurchaseInput();
        return PurchaseParser.parseInputProduct(input);
    }

    private List<PurchaseProduct> processPurchases(Products products, Map<String, Integer> purchases) {
        List<PurchaseProduct> purchaseProducts = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : purchases.entrySet()) {
            List<Product> foundProducts = products.findProductByName(entry.getKey());
            purchaseProducts.add(new PurchaseProduct(foundProducts, entry.getValue()));
        }
        return purchaseProducts;
    }

    private boolean confirmPromotionNotApplied(String productName, int quantity) {
        while (true) {
            try {
                outputView.printPromotionNotApplied(productName, quantity);
                return inputView.readYesOrNoInput();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private boolean confirmPromotionAddition(String productName) {
        while (true) {
            try {
                outputView.printPromotionAddition(productName);
                return inputView.readYesOrNoInput();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private boolean readMembershipConfirmationWithRetry() {
        while (true) {
            try {
                return inputView.readMembershipConfirmation();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private boolean readAdditionalPurchaseConfirmationWithRetry() {
        while (true) {
            try {
                return inputView.readAdditionalPurchaseConfirmation();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
