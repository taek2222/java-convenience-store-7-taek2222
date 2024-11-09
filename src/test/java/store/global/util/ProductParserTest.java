package store.global.util;

import java.util.HashMap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductParserTest {

    @Test
    void 구매할_상품의_이름과_수량을_구분한다() {
        // given
        String input = "[사이다-2]";

        // when
        HashMap<String, Integer> result = ProductParser.parseInputProduct(input);

        // then
        Assertions.assertThat(result)
                .containsEntry("사이다", 2)
                .hasSize(1);
    }
}