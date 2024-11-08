package store.view;

import static store.constant.ErrorMessage.FILE_NOT_FOUND;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.exception.FileException;

public class FileInputViewTest {

    private static final String TEST_FILE_PATH = "src/main/resources/";
    private static final String TEST_FILE_NAME = "testProducts.md";

    private FileInputView fileInputView;

    @BeforeEach
    void setup() {
        fileInputView = new FileInputView();
    }

    @AfterAll
    static void tearDown() {
        File testFile = new File(TEST_FILE_PATH + TEST_FILE_NAME);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    void 파일_내용을_입력_받아_리스트로_반환한다() {
        // given
        String fileName = "products.md";

        // when
        List<List<String>> readFile = fileInputView.readFile(fileName);

        // then
        Assertions.assertThat(readFile.getFirst())
                .isEqualTo(List.of("name", "price", "quantity", "promotion"));
    }

    @Test
    void 일치하는_파일명이_없을_경우_예외가_발생한다() {
        // given
        String fileName = "noFile.md";

        // when & then
        Assertions.assertThatThrownBy(() -> fileInputView.readFile(fileName))
                .isInstanceOf(FileException.class)
                .hasMessage(String.format(FILE_NOT_FOUND.getMessage(), fileName));
    }

    @Test
    void 파일안에_상품이_없을_경우_예외가_발생한다() throws IOException {
        // given
        createTestFileWithHeaderOnly();

        // when & then
        Assertions.assertThatThrownBy(() -> fileInputView.readFile(TEST_FILE_NAME))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private void createTestFileWithHeaderOnly() throws IOException {
        File testFile = new File(TEST_FILE_PATH + TEST_FILE_NAME);
        try (FileWriter fileWriter = new FileWriter(testFile)) {
            fileWriter.write("name,price,quantity,promotion\n");
        }
    }
}
