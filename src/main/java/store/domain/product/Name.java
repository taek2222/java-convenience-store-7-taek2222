package store.domain.product;

import static store.constant.ErrorMessage.INVALID_NAME_BLANK;
import static store.constant.ErrorMessage.INVALID_NAME_NOT_NULL;
import static store.validation.CommonValidator.validateBlank;
import static store.validation.CommonValidator.validateNull;

public class Name {

    private final String name;

    public Name(final String name) {
        validate(name);
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    private void validate(String name) {
        validateNull(name, INVALID_NAME_NOT_NULL);
        validateBlank(name, INVALID_NAME_BLANK);
    }
}
