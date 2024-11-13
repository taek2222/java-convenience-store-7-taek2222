package store.domain.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    void 이름_객체의_정보를_반환한다() {
        // given
        String productName = "콜라";
        Name name = new Name(productName);

        // when
        String toString = name.toString();

        // then
        Assertions.assertThat(toString)
                .isEqualTo(productName);
    }
}
