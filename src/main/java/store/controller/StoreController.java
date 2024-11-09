package store.controller;

import java.util.List;
import store.domain.Products;
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
        outputView.printWelcomeMessage();
        List<List<String>> inputProducts = inputView.readProductsFile();

        Products products = new Products(inputProducts);

        outputView.printProductsInfo(products.getAllProductsInfo());
    }
}
