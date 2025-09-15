package br.com.delivery.validators;

import br.com.delivery.validators.interfaces.ValueEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Stream;

public class ValueEnumValidator implements ConstraintValidator<ValueEnum, CharSequence> {

    private List<String> acceptedValues;

    private String message = "Os valores para esse campo devem ser dos tipos: #tipos";

    @Override
    public void initialize(ValueEnum annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants()).map(Enum::name).toList();
        this.message = this.message.replace("#tipos", String.join("|", acceptedValues));
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {

        context.disableDefaultConstraintViolation();

        if (value == null) {
            return true;
        }

        if (acceptedValues.contains(value.toString())) {
            return true;
        }
        context.buildConstraintViolationWithTemplate(this.message).addConstraintViolation();
        return false;
    }
}
