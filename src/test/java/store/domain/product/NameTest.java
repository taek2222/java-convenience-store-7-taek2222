package store.domain.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @Test
    void 등록_이름이_null_일_경우_예외가_발생한다() {
        Assertions.assertThatThrownBy(() -> new Name(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "테스트 이름 = [{arguments}]")
    @ValueSource(strings = {" ", ""})
    void 등록_이름이_공백일_경우_예외가_발생한다(String name) {
        Assertions.assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
