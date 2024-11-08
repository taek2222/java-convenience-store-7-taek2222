package store.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileInputViewTest {

    @Test
    void 파일_내용을_입력_받아_리스트로_반환한다() {
        // given
        String fileName = "products.md";
        FileInput fileInput = new FileInput();

        // when
        List<List<String>> readFile = fileInput.readFile(fileName);

        // then
        Assertions.assertThat(readFile)
                .isNotNull();
    }

    @Test
    void 일치하는_파일명이_없을_경우_예외가_발생한다() {
        // given
        String fileName = "noFile.md";
        FileInput fileInput = new FileInput();

        // when & then
        Assertions.assertThatThrownBy(() -> fileInput.readFile(fileName))
                .isInstanceOf(FileNotFoundException.class);
    }

    @Test
    void 파일안에_상품이_없을_경우_예외가_발생한다() throws IOException {
        // given
        String fileName = "testProducts.md";
        File testFile = new File(fileName);

        FileWriter fileWriter = new FileWriter(testFile);

        fileWriter.write("name,price,quantity,promotion\n");
        fileWriter.flush();

        fileWriter.close();

        FileInput fileInput = new FileInput();

        // when & then
        Assertions.assertThatThrownBy(() -> fileInput.readFile(fileName))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
