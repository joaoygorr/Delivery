package br.com.delivery.validators.interfaces;

import br.com.delivery.validators.ValueEnumValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValueEnumValidator.class)
@Target({ ElementType.FIELD })
public @interface ValueEnum {
    Class<? extends Enum<?>> enumClass();

    String message() default "valor inválido para o campo";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
