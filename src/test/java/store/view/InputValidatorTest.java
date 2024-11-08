package store.view;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class InputValidatorTest {

    @Test
    void 파일안에_내용이_없을_경우_예외가_발생한다1() {
        Assertions.assertThatThrownBy(() -> InputValidator.validateFileContents(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 파일안에_내용이_없을_경우_예외가_발생한다2() {
        List<List<String>> testList = List.of(List.of("name", "price", "quantity", "promotion"));

        Assertions.assertThatThrownBy(() -> InputValidator.validateFileContents(testList))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 파일안에_내용이_없을_경우_예외가_발생한다3() {
        List<List<String>> testList = List.of(List.of("name", "buy", "get", "start_date", "end_date"));

        Assertions.assertThatThrownBy(() -> InputValidator.validateFileContents(testList))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
