package store.view;

import static store.constant.ErrorMessage.FILE_CONTENT_INSUFFICIENT;
import static store.constant.ErrorMessage.FILE_CONTENT_NULL;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import store.exception.FileException;

public class InputValidatorTest {

    @Test
    void 파일안에_내용이_없을_경우_예외가_발생한다1() {
        Assertions.assertThatThrownBy(() -> InputValidator.validateNonEmptyFileContents(null))
                .isInstanceOf(FileException.class)
                .hasMessage(FILE_CONTENT_NULL.getMessage());
    }

    @ParameterizedTest(name = "테스트 파라미터 : {0}")
    @MethodSource("provideArgument")
    void 파일안에_내용이_없을_경우_예외가_발생한다2(List<List<String>> input) {
        Assertions.assertThatThrownBy(() -> InputValidator.validateNonEmptyFileContents(input))
                .isInstanceOf(FileException.class)
                .hasMessage(FILE_CONTENT_INSUFFICIENT.getMessage());
    }

    static Stream<Arguments> provideArgument() {
        return Stream.of(
                Arguments.arguments(List.of(List.of("name", "price", "quantity", "promotion"))),
                Arguments.arguments(List.of(List.of("name", "buy", "get", "start_date", "end_date")))
        );
    }
}
