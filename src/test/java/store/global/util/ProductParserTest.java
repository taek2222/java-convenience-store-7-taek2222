package store.global.util;

import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

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
}