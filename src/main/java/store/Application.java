package store;

import store.controller.StoreController;
import store.view.FileInputView;
import store.view.InputView;
import store.view.OutputView;

public class Application {
    public static void main(String[] args) {
        FileInputView fileInputView = new FileInputView();
        StoreController storeController = new StoreController(
                new OutputView(),
                new InputView(fileInputView)
        );
        storeController.run();
    }
}
