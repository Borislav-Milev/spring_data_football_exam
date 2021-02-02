package softuni.exam.util;

import softuni.exam.util.contracts.ValidatorUtil;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class ValidatorUtilImpl implements ValidatorUtil {

    private final Validator validator;

    public ValidatorUtilImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public <E> boolean isValid(E entity) {
        return this.validator.validate(entity).size() == 0;
    }

    @Override
    public <E> Set<ConstraintViolation<E>> violations(E entity) {
        return this.validator.validate(entity);
    }


}
