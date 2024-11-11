package store;

import store.controller.StoreController;
import store.global.util.FileUtil;
import store.view.InputView;
import store.view.OutputView;

public class Application {
    public static void main(String[] args) {
        FileUtil fileInputView = new FileUtil();
        StoreController storeController = new StoreController(
                new OutputView(),
                new InputView(fileInputView)
        );
        storeController.run();
    }
}
