package store.global.util;

import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ProductParserTest {

    @Test
    void 구매할_상품의_이름과_수량을_구분한다() {
        // given
        String input = "[사이다-2]";

        // when
        Map<String, Integer> result = ProductParser.parseInputProduct(input);

        // then
        Assertions.assertThat(result)
                .containsEntry("사이다", 2)
                .hasSize(1);
    }

    @Test
    void 구매할_상품이_중복될_시_수량을_합산한다() {
        // given
        String input = "[사이다-2],[사이다-2]";

        // when
        Map<String, Integer> result = ProductParser.parseInputProduct(input);

        // then
        Assertions.assertThat(result)
                .containsEntry("사이다", 4)
                .hasSize(1);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "[사이다-한개]",
            "[사이다]",
            "[사이다2]",
            "[사이다-1],[사이다-2",
            "사이다-2",
            "[-2]",
            "[사이다-]",
            "[사이다-abc]",
            "[사이다-2]abc",
            "[[사이다-2]]",
            "[123-4]",
            "[사이다:2]",
            "[사이다--2]",
            "[사이다-2][사이다-1]",
            "[사이다-1],[사이다-2],"
    })
    void 구매할_상품의_형식이_올바르지_않을_경우_예외가_발생한다(String input) {
        Assertions.assertThatThrownBy(() -> ProductParser.parseInputProduct(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}