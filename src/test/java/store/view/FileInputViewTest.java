package store.view;

import static store.constant.ErrorMessage.FILE_NOT_FOUND;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.exception.FileException;

public class FileInputViewTest {

    private FileInputView fileInputView;

    @BeforeEach
    void setup() {
        fileInputView = new FileInputView();
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
}
